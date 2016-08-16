package Semantic;
import java.io.*;
import java.util.*;

public class SymTable
{
    public final String INT;
    public final String STRING;
    public final String ERROR;
    public final String BOOL;
    public final String VOID;
    public final String CHAR;

    private LocalScope scope;
    private ArrayList<Type> global_types;
    private ArrayList<Type> func_types;
    private ArrayList<Class> classes;
    public Class actual_class;
    private Hashtable<String,Type> alias_types;
    private LocalScope global_scope;
    private boolean objacc;
    private boolean firstexpr;
    public String last_class;


    public SymTable () {
        scope = new LocalScope ();
        global_scope = scope;
        global_types = new ArrayList<Type> ();
        actual_class = null;
        alias_types = new Hashtable<String,Type> ();
        classes = new ArrayList<Class>();
        func_types = new ArrayList<Type> ();
        objacc = false;
        firstexpr = false;
        last_class = "";

        INT = "Int";
        STRING = "String";
        BOOL = "Bool";
        CHAR = "Char";
        VOID = "Void";
        ERROR = ".Error";
    }

    public void enterObjAccess() {
        objacc = true;
    }

    public void exitObjAccess() {
        objacc = false;
        firstexpr = false;
    }

    public boolean inObjAccess() {
        return objacc;
    }

    public boolean getFirstExpr() {
        return firstexpr;
    }

    /* Checks if the Id exists in the actual scope or an upper scope */
    public boolean checkId (String id, boolean constant) {
        LocalScope aux = scope;

        while (aux != null) {
            if (aux.checkId(id,constant)){
                return true;
            }
            aux = aux.getParent();
        }

        return false;
    }

    public boolean checkIdGlobal(String id, boolean constant) {
        if(global_scope.checkId(id,constant))
            return true;
        else
            return false;
    }

    /* Retrieves the type of the Id in the actual scope or an upper scope */
    public Type getIdType (String id) {
        LocalScope aux = scope;
        while (aux != null) {
            if (aux.checkId(id,true)){
                return aux.getIdType (id);
            }
            aux = aux.getParent();
        }

        return null;
    }

    public Type getIdType (String id, boolean actual) {
        if(actual)
            return scope.getIdType(id);
        else
            return null;
    }

    /* Adds a variable to the actual scope */
    public void addVariable (String id, Type type) {
        scope.addVariable(id, type);
        //System.out.println("id: " + id);
    }

    /* Checks if a variable or a constant exist with name id */
    public boolean checkVariable (String id) {
        return scope.checkVariable(id);
    }

     /* Adds a constant to the actual scope */
    public void addConstant (String id, Type type) {
        scope.addConstant(id, type);
    }

    public void addConstantGlobal (String id, Type type) {
        global_scope.addConstant(id, type);
    }

    /* Checks if a variable or a constant exist with name id */
    public boolean checkConstant (String id) {
        return scope.checkConstant(id);
    }

    public void addSubScope () {
        scope = scope.addChildScope();
    }

    public void exitSubScope () {
        LocalScope aux = scope.getParent();
        if(aux != null)
            scope = aux;
    }

    public void eraseSubScope () {
        LocalScope aux = scope;
        scope = scope.getParent();
        scope.eraseChildScope(aux);
    }

    public void scopeVariablesToAttributes () {
        Iterator it = scope.getVariables().entrySet().iterator();
        ArrayList<String> aux = scope.getConstants();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if(aux.contains(pair.getKey()))
                addClassAttribute((String)pair.getKey(),(Type)pair.getValue(),false);
            else
                addClassAttribute((String)pair.getKey(),(Type)pair.getValue(),true);
        }
    }

    public void classAttributesToVariables () {
        for(Attribute a : actual_class.attributes) {
            addVariable(a.id,a.type);
        }
    }

    /* Adds a new Type, it can be a class type or a func type */
    public boolean addType (Type type) {
        if(isPredefinedType(type.name))
            return false;
        
        for(Type t : global_types) {
            if(t.name.equals(type.name))
                return false;
        }
        global_types.add(type);
        return true;
    }

    public boolean addFuncType (Type type) {
        func_types.add(type);
        return addType(type);
    }

    public boolean isFuncType (String name) {
        for(Type t : func_types){
            if(t.name.equals(name))
                return true;
        }

        return false;
    }

    public boolean isReacheable(String name1, String name2) {
        Class aux = null;
        for(Class c : classes) {
            if(c.name.equals(name1))
                aux = c;
        }

        if(aux != null)
            aux = getClass(aux.parent);

        while(aux != null) {
            if(aux.name.equals(name2))
                return true;

            if(aux.name.equals(name1))
                return false;
            aux = getClass(aux.parent);
        }

        return false;
    }

    public Class getClass(String name) {
        for(Class c : classes) {
            if(c.name.equals(name))
                return c;
        }

        return null;
    }

    /* Checks if the type already exist */
    public boolean existClassType (Type type) {
        for(Type t : global_types) {
            if(t.name.equals(type.name)) {
                return true;
            }
        }
        return false;
    }

    public void printClasses() {
        System.out.println("Clases:");
        for(Type t: global_types) {
            System.out.println(t.name);
        }
        System.out.println("Fin Clases");

    }

    public void printAttributes () {
        for(Class c : classes) {
            System.out.println("Clase " + c.name);
            for(Attribute a : c.attributes) {
                System.out.println(a.id);
            }
            System.out.println("fin clase");
        }
    }

    public void printClassAttributes(String name) {
        Class aux = getClass(name);
        System.out.println("Atributos");
        for(Attribute a : aux.attributes)
            System.out.println(a.id);
    }

    public void printScopeVariables () {
        Iterator it = scope.getVariables().entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println((String)pair.getKey());
        }
    }

    public void printScopeVariables (LocalScope scope) {
        Iterator it = scope.getVariables().entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println((String)pair.getKey());
        }
    }


    public Type getClassAttribute (String clase, String name, boolean mut) {
        Class aux = null;
        Type r = null;
        for(Class c : classes) {
            if(c.name.equals(clase)){
                aux = c;
                break;
            }
        }
        if(aux != null) {
            if(mut) {
                if(aux.mut) {
                    for(Attribute a : aux.attributes) {
                        if(a.id.equals(name)) {
                            if(a.mut) {
                                r = a.type;
                            }
                            break;
                        }
                    }
                }
            }
            else {
                for(Attribute a : aux.attributes) {
                    if(a.id.equals(name)) {
                        if(a.mut) {
                            r = a.type;
                        }
                        break;
                    }
                }
            }
        }

        return r;
    }
    

    public boolean existAlias (String name) {
        if(alias_types.containsKey(name))
            return true;
        else 
            return false;
    }

    public void addAlias (String name, Type type) {
        alias_types.put(name,type);
    }

    public boolean isPredefinedType(String type) {

        if( type.equals(INT) || type.equals(CHAR) || type.equals(STRING) || type.equals(BOOL) ) {
            return true;
        }
        else {
            return false;
        }
    }

    /* The next methods constructs the Class Information and keeps data of the actual
        class that is being constructed (null if there isn't one)  */

    /* Adds a new struct class */
    public void enterClass(String name, String mut) {
        Class aux = new Class();
        aux.name = name;
        if (mut != "")
            aux.mut = true;
        else
            aux.mut = false;

        classes.add(aux);

        actual_class = aux;
    }

    public void enterClass(String name) {
        for(Class c : classes) {
            if(c.name.equals(name)) {
                actual_class = c;
                break;
            }
        }
    }

    /* Adds the name of the parent to the actual class */
    public void addClassParent(String name) {
        actual_class.parent = name;
    }

    /* Adds the type of the param and adds it as an attribute to the actual class*/
    public void addClassParam(String name, Type type) {
        actual_class.params.addFirst(type);
        addClassAttribute(name,type,true);
    }

    /* Adds an attribute to the actual class */
    public void addClassAttribute(String name, Type type, boolean mut) {
        Attribute aux = new Attribute();
        aux.id = name;
        aux.type = type;
        if ( mut )
            if ( actual_class.mut )
                aux.mut = true;
            else
                aux.mut = false;
        else
            aux.mut = false;
        actual_class.attributes.add(aux);
    }

    public boolean checkAttribute(String name) {

        for(Attribute a : actual_class.attributes) {
            if (a.id.equals(name)) {
                return true;
            }
        }

        return false;
    }

    public boolean checkAttributeMutable(String name) {
        for(Attribute a : actual_class.attributes) {
            if (a.id.equals(name)) {
                if(a.mut)
                    return true;
                break;
            }
        }

        return false;
    }

    public Type getAttributeType (String name) {
        for(Attribute a : actual_class.attributes) {
            if (a.id.equals(name)) {
                return a.type;
            }
        }

        return null;
    }

    public void addParentClassAttributes () {
        Class parent = getClass(actual_class.parent);
        if(parent.attributes != null)
            for(Attribute a : parent.attributes)
                addClassAttribute(a.id, a.type, a.mut);
    }

    /* Exit the actual class */
    public void exitClass() {
        actual_class = null;
    }

    public boolean inClass() {
        if(actual_class != null)
            return true;
        else
            return false;
    }

    public boolean isOfClassType(String type) {
        if(actual_class.name.equals(type))
            return true;
        else
            return false;
    }

    public ArrayDeque<Type> getParamsClass(String clase) {
        for(Class c : classes) {
            if(c.name.equals(clase)) {
                return c.params;
            }
        }
        return null;
    }

    public boolean checkActualClass() {
        if(actual_class == null) {
            return false;
        }
        else
            return true;
    }

    public boolean isCycle() {
        Class aux = getClass(actual_class.parent);
        String name = actual_class.name;
        while(aux != null) {
            if(aux.name.equals(name))
                return true;
            aux = getClass(aux.parent);
        }

        return false;
    }

    public void createFile() {
        try {
            File file = new File("output");
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write("------------Variables, constantes y scope----------\n");
            LocalScope actual_scope = scope;
            writeScopes(writer, actual_scope, "", "", -1);
            writer.write("\n------------Tipos globales----------\n");
            String string = "";
            for(Type t : global_types) {
                string += t.name + "\n";
            }
            writer.write(string);
            string = "";
            writer.write("\n------------Clases y herencia----------\n");
            for(Class c : classes) {
                string += c.name;
                if(!c.parent.equals(""))
                    string += " |Parent--> " + c.parent;
                string += "\n";
                string += "     Attributes:\n";
                for(Attribute a : c.attributes) {
                    string += "     " + a.id + "\n";
                }
            }
            writer.write(string);
            writer.close();
        } catch (IOException ex) {System.out.println(ex);}

    }

    public void writeScopes (BufferedWriter writer, LocalScope scope, String tab, String num, int n) {
        if(n == -1)
            num += "0";
        else
            num += "_" + n;

        String string = "\n" + tab + "Scope " + num + "\n";

        string += tab + "Variables:\n";

        try {
            writer.write(string);
        } catch (IOException ex) {System.out.println(ex);}

        Map<String,Type> variables = scope.getVariables();
        ArrayList<String> constants = scope.getConstants();

        Iterator it = variables.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if(!constants.contains((String) pair.getKey()))
                try {
                    writer.write(tab + "  " +(String)pair.getKey() + "\n");
                } catch (IOException ex) {System.out.println(ex);}
        }

        string = tab + "Constants:\n";

        for(String c : constants) {
            string += tab + "   " + c + "\n";
        }

        try {
            writer.write(string);
        } catch (IOException ex) {System.out.println(ex);}


        int i = 0;
        for(LocalScope s : scope.getChildScopes()) {
            writeScopes(writer, s, tab + "  ", num ,i);
            i++;
        }
    }

    private class LocalScope
    {
        private LocalScope parent_scope = null;
        private ArrayList<LocalScope> child_scopes;
        private Map<String,Type> variables;
        private ArrayList<String> constants;

        public LocalScope () {
            variables = new HashMap<String,Type> ();
            child_scopes = new ArrayList<LocalScope> ();
            constants = new ArrayList<String> ();
        }

        public boolean checkId (String id, boolean constant) {
            if(variables.containsKey (id)) {
                if(!constant) {
                    return !constants.contains(id);
                }
                else {
                    return true;
                }
            }
            else {
                return false;
            }
        }

        public Type getIdType (String id) {
            return variables.get (id);
        }

        public void addVariable (String id, Type type) {
            variables.put (id, type);
        }

        public boolean checkVariable (String id) {
            return variables.containsKey(id);
        }

        public boolean checkConstant (String id) {
            return constants.contains(id);
        }

        public void addConstant (String id, Type type) {
            variables.put (id, type);
            constants.add(id);
        }

        public void setParent (LocalScope parent) {
            parent_scope = parent;
        }

        public LocalScope getParent () {
            return parent_scope;
        }

        public Map<String,Type> getVariables () {
            return variables;
        }

        public ArrayList<String> getConstants () {
            return constants;
        }

        public LocalScope addChildScope () {
            LocalScope scope = new LocalScope ();
            scope.setParent(this);
            child_scopes.add(scope);
            return scope;
        }

        public ArrayList<LocalScope> getChildScopes () {
            return child_scopes;
        }

        public void eraseChildScope(LocalScope child) {
            child_scopes.remove(child);
        }
    }

    private class Class {
        public String parent = "";
        public String name = null;
        public boolean mut = true;
        public ArrayDeque<Type> params = new ArrayDeque<Type> ();
        public ArrayList<Attribute> attributes = new ArrayList<Attribute> ();
    }

    private class Attribute {
        public String id  = null;
        public boolean mut = true;
        public Type type  = null;
    }
}