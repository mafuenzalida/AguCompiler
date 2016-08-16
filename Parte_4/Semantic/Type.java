package Semantic;
import java.util.*;
import java.io.*;

public class Type {

	public String name;
	public TypeStruct struct;
	public boolean comp;
	public final String INT = "Int";
    public final String STRING = "String";
    public final String BOOL = "Bool";
    public final String VOID = "Void";
    public final String CHAR = "Char";

    public Type (String name) {
    	this.name = name;
    	this.struct = new TypeStruct();
    	this.struct.name = name;
    	this.comp = false;
    }

	public Type (ArrayList<Type> types) {
		struct = new TypeStruct();
		TypeStruct aux = struct;
		this.comp = true;
		name = "";
		int rp = 0;
		for(int i = 0; i < types.size()-1; i++) {
			aux.left = new TypeStruct();
			if(comp)
				aux.left = types.get(i).struct;
			else
				aux.left.name = types.get(i).name;
			name += "(" + aux.left.name + ".";
			rp++;
			aux.right = new TypeStruct();
			if(i != types.size()-2) {
				aux = aux.right;
			}
			else {
				if(comp)
					aux.right = types.get(i+1).struct;
				else
					aux.right.name = types.get(i+1).name;
				name += aux.right.name;
				for(int j = 0; j<rp; j++) {
					name += ")";
				}
			}
		}
	}

	public boolean valid (SymTable symtable, boolean alias) {
		TypeStruct aux = struct;
		if(aux.name != "") {
			return validType(aux.name, symtable, false, alias);
		}
		
		if (validRecursiveLeft(aux.left,symtable,alias) && validRecursiveRight(aux.right,symtable,alias))
			return true;

		return false;
	}

	private boolean validRecursiveLeft (TypeStruct aux, SymTable symtable, boolean alias) {
		if(aux.name != "") {
			return validType(aux.name, symtable, false, alias);
		}
		if (validRecursiveLeft(aux.left,symtable,alias) && validRecursiveRight(aux.right,symtable,alias))
			return true;

		return false;
	}

	private boolean validRecursiveRight (TypeStruct aux, SymTable symtable, boolean alias) {
		if(aux.name != "") {
			return validType(aux.name, symtable, true, alias);
		}
		if (validRecursiveLeft(aux.left,symtable,alias) && validRecursiveRight(aux.right,symtable,alias))
			return true;

		return false;
	}

	public boolean validType(String name, SymTable symtable, boolean right, boolean alias) {
		//Predefinido
		if (symtable.isPredefinedType(name))
			return true;
		else if (right && name.equals(VOID))
			return true;

		//Alias
		if(alias && symtable.existAlias(name))
			return true;

		//Clase
		return symtable.existClassType(new Type(name));
	}

	public boolean isSubTypeOf(Type type, SymTable symtable) {
		return isSubTypeOf(struct, type.struct,symtable);
	}

	private boolean isSubTypeOf(TypeStruct struct1, TypeStruct struct2, SymTable symtable) {
		if(!struct1.name.equals("") && !struct2.name.equals("")) {
			if(struct1.name.equals(struct2.name))
				return true;

			if(symtable.isReacheable(struct1.name,struct2.name))
				return true;
		}

		//Func types
		if(symtable.isFuncType(struct1.name) && symtable.isFuncType(struct2.name)) {
			if(isSubTypeOf(struct2.left,struct1.left,symtable)) {
				if(symtable.isFuncType(struct1.right.name) && symtable.isFuncType(struct2.right.name)) {
					if(isSubTypeOf(struct2.right, struct1.right, symtable))
						return true;
					else if(isSubTypeOf(struct1.right, struct2.right, symtable))
						return true;
				}
			}
		}

		return false;
	}

	public boolean is_equal(Type type) {
		if(name.equals(type.name))
			return true;
		else
			return false;
	}
	private class TypeStruct {
		public String name = "";
		public TypeStruct left = null;
		public TypeStruct right = null;
	}
	
}