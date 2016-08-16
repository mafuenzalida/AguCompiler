package Semantic;
import java.io.*;
import java.util.*;
import Tree.*;

public class TypeChecker implements TreeVisitor {

    /* Tabla de símbolos, manejo de errores y tipos de clase/funciones*/
    private SymTable symtable;
    private ErrorManager error;
    private int mode ; //0 = creation, 1 = validation

    public TypeChecker (SymTable symtable, ErrorManager error) {
        this.symtable = symtable;
        this.error = error;
        mode = 0;
    }

    public void check (ASTree root) {
        //mode = 0 
        root.accept (this);

        mode = 1;

        //mode = 1 global types are ready
        root.accept(this);
    }


    public void visit (TypeID node) {
        if(mode == 0) {
            node.type = new Type(node.getName());
        }
    }

    public void visit (StringExpr node) {
        if(mode == 0)
            node.type = new Type(symtable.STRING);
    }

    public void visit (IntExpr node) {
        if(mode == 0)
            node.type = new Type(symtable.INT);
    }

    public void visit (BoolExpr node) {
        if(mode == 0)
            node.type = new Type(symtable.BOOL);
    }

    public void visit (CharExpr node) {
        if(mode == 0)
            node.type = new Type(symtable.CHAR);
    }

    public void visit (IdExpr node) {
        if(mode == 0) {
            String id = node.getName ();
            if (!symtable.checkId (id, true)) {
                error.addError (node.pos, "Variable <<" + id
                                + ">> no está definida");
                node.type = symtable.getIdType (symtable.ERROR);
            }
            else {
                node.type = symtable.getIdType (id);
            }
        }
    }
    public void visit (DirectAccess node) {
        if(mode == 0) {
            //Dentro de clase
            if(!symtable.inClass()) {
                error.addError(node.pos, "Expresion invalida fuera de una clase");
            }
        }
        else if(mode == 1) {

            //Clase contenga atributo y nodo queda con tipo del atributo
            node.type = symtable.getAttributeType(node.getName());
            if(node.type == null) {
                error.addError(node.pos, "La clase no contiene un atributo con id <<" + node.getName() + ">>");
            }

        }
    }


    public void enter (Program node) {
    }
    public void exit  (Program node) {
        if(mode == 1) {
            //symtable.printAttributes();
        }
    }

    public void enter (TypeRA node) {
    }
    public void exit  (TypeRA node) {
        if(mode == 0){
            ArrayList<Type> aux = new ArrayList<Type> ();
            aux.add(node.typel.type);
            aux.add(node.typer.type);
            node.type = new Type(aux);
        }
        if(mode == 1)
            if(!node.type.valid(symtable,true))
                error.addError(node.pos, "Tipo <<" + node.type.name + ">> invalido");
    }

    public void enter (TypeP node) {
    }
    public void exit  (TypeP node) {
        if(mode == 0)
            node.type = node.getDefType().type;
    }

    public void enter (AliasDecl node) {
    }
    public void exit  (AliasDecl node) {
        String name = node.getName();
        if(mode == 0) {
            node.type = new Type(name);
            if(!symtable.existAlias(name)) {
                symtable.addAlias(name,node.getDefType().type);
            }
            else {
                error.addError(node.pos, "Alias <<" + name + ">> ya está definido");
            }
        }
        else if(mode == 1) {
            //TODO: Chequear que no sea tipo de clase ni predefinido
            if(node.type.validType(name,symtable,true,false))
                error.addError(node.pos, "Tipo <<" + name + ">> invalido, es tipo clase o predefinido");
            if(!node.getDefType().type.valid(symtable,false))
                error.addError(node.pos, "Tipo <<" + node.getDefType().type.name + ">> no es valido, " +
                    "se usa algun tipo que no existe o se usa alias");
        }
    }

    public void enter (VarDecl node) {
    }
    public void exit  (VarDecl node) {
        if(mode == 0) {
            String name = node.getName();
            node.type = node.getDefType().type;

            if(!symtable.inClass()) {
                if(symtable.checkVariable(name))
                    error.addError(node.pos, "Variable <<" + name + ">> ya esta definida en el scope");
                else
                    symtable.addVariable(name, node.type);
            }
            else {
                if (symtable.checkAttribute(name))
                    error.addError(node.pos, "Ya existe un atributo con nombre <<" + name + ">>");
                else
                    symtable.addVariable(name, node.type);
            }
        }
        else if (mode == 1) {
            if(node.getExpression() != null) {
                if(!node.getExpression().type.isSubTypeOf(node.type,symtable)) {
                    error.addError(node.pos, "El tipo de inicializacion <<" + node.getExpression().type.name + ">>" +
                        "no es subtipo de <<" + node.type.name + ">> tipo de la variable");
                }
            }
        }
    }

    public void enter (ConstDecl node) {
    }
    public void exit  (ConstDecl node) {
        if(mode == 0) {
            String name = node.getName();
            node.type = node.getDefType().type;
            if(!symtable.inClass()) {
                if(symtable.checkConstant(name))
                    error.addError(node.pos, "Constante <<" + name + ">> ya esta definida en el scope");
                else
                    symtable.addConstant(name, node.type);
            }
            else {
                if (symtable.checkAttribute(name))
                    error.addError(node.pos, "Ya existe un atributo con nombre <<" + name + ">>");
                else
                    symtable.addConstant(name, node.type);
            }
        }
        else if (mode == 1) {
            if(!node.getExpression().type.isSubTypeOf(node.type,symtable))
                error.addError(node.pos, "El tipo de inicializacion <<" + node.getExpression().type.name + ">>" + 
                    "no es subtipo de <<" + node.type.name + ">> tipo de la variable");
        }
    }

    public void enter (Assignment node) {

    }
    public void exit  (Assignment node){ 
        String param = node.getParameter();
        ArrayList<String> names = node.getNames();
        if(mode == 0) {
            node.type = new Type(symtable.VOID);
            if(param == "") {
                if(!symtable.checkId(names.get(0),false)) {
                    error.addError(node.pos, "Variable <<" + names.get(0) + ">> no ha sido declarada");
                }
            }
        }
        else if(mode == 1) {
            //Chequeo de tipos de clases y atributos mutables
            if(param == "") {
                if(symtable.checkId(names.get(0),false)) {
                    String clase = symtable.getIdType(names.get(0)).name;
                    Type att_type = null;

                    for(int i = 0; i<names.size()-1; i++) {

                        att_type = symtable.getClassAttribute(clase, names.get(i+1),true);
                        if(att_type == null) {
                            error.addError(node.pos, "Algun atributo no esta declarado o no es mutable");
                            break;
                        }
                        clase = att_type.name;
                    }

                    if(att_type == null)
                        att_type = new Type(clase);
                    //Expression debe ser subtipo de T_n
                    if(!node.getExpression().type.isSubTypeOf(att_type,symtable))
                        error.addError(node.pos, "La expresion dada no es subtipo del atributo <<" + names.get(names.size()-1) + ">>");
                }
            }
            else {
                if(!symtable.inClass()) {
                    error.addError(node.pos, "Asignacion a atributo <<" + names.get(0) + ">> esta fuera de una definicion de clase");
                }
                else {
                    String clase = null;
                    if(symtable.getAttributeType(names.get(0)) == null) {
                        error.addError(node.pos, "Algun atributo no esta declarado");
                    }
                    else {
                        clase = symtable.getAttributeType(names.get(0)).name;
                    }

                    if(clase != null) {

                        Type att_type = null;
                        for(int i = 0; i<names.size()-1; i++) {
                            att_type = symtable.getClassAttribute(clase, names.get(i+1), true);
                            if(att_type == null){
                                error.addError(node.pos, "Algun atributo no esta declarado o no es mutable");
                                break;
                            }

                            clase = att_type.name;
                        }
                        if(att_type == null)
                            att_type = new Type(clase);


                        //Expression debe ser subtipo de T_n
                        if(att_type != null && !node.getExpression().type.isSubTypeOf(att_type,symtable))
                            error.addError(node.pos, "La expresion dada no es subtipo del atributo <<" + names.get(names.size()-1) + ">>");
                        
                        if(!symtable.checkAttributeMutable(names.get(0))) {
                            error.addError(node.pos, "Atributo <<" + names.get(0) + ">> no ha sido definido en la clase actual" +
                                " o este no es mutable");
                        }
                    }
                }
            }
        }
    }

    public void enter (ClassCtor node) {
        if(mode == 0) {
            symtable.addSubScope();
            symtable.classAttributesToVariables();
        }
    }
    public void exit  (ClassCtor node) {
        if(mode == 0) {
            symtable.exitSubScope();
            symtable.addClassParent(node.getName());
        }
        else if(mode == 1) {
            //Numero de argumentos
            ArrayDeque<Type> params = symtable.getParamsClass(node.getName());
            ArrayList<ASTree> exprs = node.getExpressions();
            
            if(params.size() != exprs.size())
                error.addError(node.pos, "El constructor pide " + params.size() + " y se dieron " + exprs.size() + " argumentos");
   
            symtable.addParentClassAttributes();
            if(symtable.isCycle())
                error.addError(node.pos, "La clase genera un ciclo no valido.");
        }
    }

    public void enter (ConstrArgDec node) {
    }
    public void exit  (ConstrArgDec node) {
        if(mode == 0) {
            String name = node.getName();
            if (!symtable.checkAttribute(name)) {
                symtable.addClassParam(name, node.getDefType().type);
            }
            else {
                error.addError(node.pos, "Ya existe un atributo en la clase con id <<" + name + ">>");
            }
        }
    }

    public void enter (ConstrArgs node) {
    }
    public void exit  (ConstrArgs node) {
    }

    public void enter (ClassDefBody node) {
        if(mode == 0)
            symtable.addSubScope();
    }
    public void exit  (ClassDefBody node) {
        if(mode == 0) {
            symtable.scopeVariablesToAttributes();
            symtable.eraseSubScope();
        }
    }

    public void enter (ClassDef node) {
        if(mode == 0){
            node.type = new Type(node.getName());
            symtable.enterClass(node.getName(), node.getParameter());
            if(!symtable.addType(node.type)) {
                error.addError(node.pos, "El id <<" + node.getName() + ">> ya existe.");
            }
        }
        else if (mode == 1) {
            symtable.enterClass(node.getName());
        }
    }
    public void exit  (ClassDef node) {
        if(mode == 0){
            symtable.exitClass();
        }
        else if(mode == 1) {
            symtable.exitClass();
        }
    }

    public void enter (FuncDef node) {
        if(mode == 0) {
            symtable.addSubScope();
        }
    }
    public void exit  (FuncDef node) {
        String name = node.getName();
        ArrayList<Type> arg_types = new ArrayList<Type>();
        for(FuncArg arg: node.getArguments()) {
            arg_types.add(arg.type);
        }
        TypeTree aux = node.getReturnType();
        if(aux != null)
            arg_types.add(aux.type);
        else
           arg_types.add(new Type("Void"));

        Type aux2 = new Type(arg_types);

        if(mode == 0) {
            if(aux != null)
                node.type = aux.type;
            else
                node.type = new Type("Void");
            

            symtable.addFuncType(aux2);

            //If func is a method of a class, the first argument must be of the class type
            if(symtable.inClass())
                if(!symtable.isOfClassType(arg_types.get(0).name))
                    error.addError(node.pos, "Primer argumento de la funcion <<" + name + ">> no es del tipo de la clase");

            //Func id not equals to any variable, constant or func in the global scope
            if(symtable.checkIdGlobal(name,true)) {
                error.addError(node.pos, "El id de la funcion <<" + name + ">> ya existe en el scope global");
            }

            //Add the func as a constant in the global type
            symtable.addConstantGlobal(name, node.type);

            symtable.exitSubScope();
        }
        else if(mode == 1) {
            if(!aux2.valid(symtable,true))
                error.addError(node.pos, "Tipo de funcion <<" + name + ">> no valido");
        }
    }

    public void enter (FuncArg node) {
    }
    public void exit  (FuncArg node) {
        if(mode == 0) {
            String name = node.getName();
            node.type = node.getDefType().type;
            if(!symtable.checkId(name,true)) {
                if(symtable.isPredefinedType(node.type.name)) {
                    symtable.addConstant(name, node.type);
                }
                else {
                    symtable.addVariable(name, node.type);
                }
            }
            else 
                error.addError(node.pos, "Id <<" + name + ">> ya esta dentro de los parametros de la funcion");
            //TODO: VER QUE SI EL TIPO ES DE FUNCION, ESTE SE GUARDA COMO CONSTANTE
        }
    }

    public void enter (StmtWhile node){
    }
    public void exit  (StmtWhile node){
        if(mode == 0) {
            node.type = new Type(symtable.VOID);
            if(!node.getExpression().type.name.equals(symtable.BOOL))
                error.addError(node.pos, "La expresion no es del tipo bool");
        }
    }

    public void enter (DefIf node){
    }
    public void exit  (DefIf node){
        if(mode == 0){
            if(node.getTBody().type.name.equals(node.getEBody().type.name))
                node.type = node.getTBody().type;
            else
                node.type = new Type(symtable.VOID);
            if(!node.getExpression().type.name.equals(symtable.BOOL))
                error.addError(node.pos, "La expresion no es del tipo bool");
        }
    }

    public void enter (DefWhen node) {
    }
    public void exit  (DefWhen node) {
        if(mode == 0){
            node.type = new Type(symtable.VOID);
            if(!node.getExpression().type.name.equals(symtable.BOOL))
                error.addError(node.pos, "La expresion no es del tipo bool");
        }
    }

    public void enter (ParExpr node) {
    }
    public void exit  (ParExpr node) {
        if(mode == 0)
            node.type = node.getExpression().type;
    }

    public void enter (UnaryExpr node) {
    }
    public void exit  (UnaryExpr node) {
        if(mode == 0) {
            Type type_expr = node.getExpression().type;
            node.type = type_expr;
            if(node.getOperator().equals("NOT")) {
                if(!type_expr.name.equals(symtable.BOOL))
                    error.addError(node.pos, "La expresion debe ser de tipo bool");
            }
            else {
                if(!type_expr.name.equals(symtable.INT))
                    error.addError(node.pos, "La expresion debe ser de tipo int");
            }
        }
    }

    public void enter (BinaryExpr node) {
    }
    public void exit  (BinaryExpr node) {
        if(mode == 0) {
            Type type_lexpr = node.getLeftExpression().type;
            Type type_rexpr = node.getRightExpression().type;
            if(type_lexpr == null) {
                System.out.println("left");
            }
            if(type_rexpr == null) {
                System.out.println("right");
            }
            node.type = type_lexpr;
            String oper = node.getOperator();
            if(oper.equals("+") || oper.equals("-") || oper.equals("/") || oper.equals("*") || oper.equals("%")) {
                if(!type_lexpr.name.equals(symtable.INT) || !type_rexpr.name.equals(symtable.INT)) {
                    System.out.println(type_lexpr.name + " " + type_rexpr.name+"s");
                    error.addError(node.pos, "Una de las expresiones (o ambas) no son del tipo INT");
                }
            }
            else if(oper.equals("&&") || oper.equals("||")) {
                if(!type_lexpr.name.equals(symtable.BOOL) || !type_rexpr.name.equals(symtable.BOOL))
                    error.addError(node.pos, "Una de las expresiones (o ambas) no son del tipo BOOL");
            }
            else {
                node.type = new Type(symtable.BOOL);
                if(!type_lexpr.name.equals(symtable.INT) || !type_rexpr.name.equals(symtable.INT))
                    error.addError(node.pos, "Una de las expresiones (o ambas) no son del tipo INT");
            }
        }
    }

    public void enter (ObjDef node) {
    }
    public void exit  (ObjDef node) {
        String name = node.getName();
        if(mode == 0) {
            node.type = new Type(name);
        }
        else if(mode == 1) {
            //Tipo de clase valido
            if(!symtable.existClassType(new Type(name))) {
                error.addError(node.pos, "El type id <<" + name + ">> no es de clase existente");
            }
            else {
                //Numero de argumentos
                ArrayDeque<Type> params = symtable.getParamsClass(name);
                ArrayList<ASTree> exprs = node.getExpressions();
                
                if(params.size() != exprs.size())
                    error.addError(node.pos, "El constructor pide " + params.size() + " y se dieron " + exprs.size() + " argumentos");
                //Subtipos
                if(params.size() == exprs.size()){
                    Iterator it = params.iterator();
                    int i = params.size()-1;
                    while(it.hasNext()) {
                        Type aux = (Type) it.next();
                        if(!exprs.get(i).type.isSubTypeOf(aux, symtable)) {
                            error.addError(node.pos, "El parametro " + i + " de tipo <<" + 
                                exprs.get(i).type.name + ">> no es subtipo del argumento correspondiente de tipo <<" + aux.name + ">>");
                            break;
                        }
                        i--;
                    }
                }
            }

        }
    }

    public void enter (FuncCall node) {
    }
    public void exit  (FuncCall node) {
        if(mode == 1) {
            ASTree expr = node.getExpression();
            ArrayList<ASTree> exprs = node.getExpressions();
            ArrayList<Type> aux = new ArrayList<Type> ();
            for(ASTree e : exprs) {
                aux.add(e.type);
            }
            aux.add(expr.type);

            if(symtable.existClassType(expr.type)) {
                node.type = expr.type;
            }
            else {
                if(!symtable.isFuncType((new Type(aux)).name)) {
                    error.addError(node.pos, "Hay problemas con la llamada a la funcion.");
                    node.type = new Type(symtable.ERROR);
                }
                else {
                    node.type = expr.type;
                }
            }
        }
    }

    public void enter (LetExprDef node) {
        if(mode == 0)
            symtable.addSubScope();
    }
    public void exit  (LetExprDef node) {
        if(mode == 0) {
            node.type = node.getBody().type;
            symtable.exitSubScope();
        }

    }

    public void enter (LetExpr node) {
    }
    public void exit  (LetExpr node) {
        if(mode == 0) {
            node.type = node.getDefType().type;
            String name = node.getName();
            if(symtable.checkId(name,true)) {
                error.addError(node.pos, "Id <<" + name + ">> ya existe en la expresion let");
            }
            else {
                if(symtable.isPredefinedType(node.type.name)) {
                    symtable.addConstant(name, node.type);
                }
                else {
                    symtable.addVariable(name, node.type);
                }
            }
        }
        else if(mode == 1) {
            //Subtipo expresiones

        }
    }

    public void enter (BlockExpr node) {
        if(mode == 0) {
            symtable.addSubScope();
        }
    }
    public void exit  (BlockExpr node) {
        if(mode == 0) {
            ArrayList<ASTree> aux = node.getStmts();
            if(aux.size() > 1 && aux.get(aux.size()-1).e == true)
                node.type = aux.get(aux.size()-1).type;
            else
                node.type = new Type("Void");
            symtable.exitSubScope();
        }

    }

    public void enter (Body node) {
        
    }
    public void exit  (Body node) {
        if(mode == 0) {
            node.type = node.getExpression().type;
        }
    }

    public void enter (CondExpr node) {
    }
    public void exit  (CondExpr node) {
        if(mode == 0)
            node.type = node.getExpression().type;
    }

    public void enter (BodyExpr node) {
    }
    public void exit  (BodyExpr node) {
        if(mode == 0)
            node.type = node.getExpression().type;
    }

}