package Tree;
import java.util.ArrayList;
import Parser.Position;

public class ConstrArgDec extends DeclTree
{
    private String parameter = "";
	private String name;
	private TypeTree deftype;

	public ConstrArgDec (String parameter, String name, TypeTree type, Position pos) {
        this.parameter = parameter;
        this.name = name;
        this.deftype = type;
        this.pos = pos;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        deftype.accept (visitor);
        visitor.exit (this);
    }

    public String getParameter() {
        return parameter;
    }

    public String getName() {
        return name;
    }

    public TypeTree getDefType () {
        return deftype;
    }
}