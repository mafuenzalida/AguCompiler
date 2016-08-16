package Tree;
import Parser.Position;

public class CharExpr extends ExprTree
{
    private String character;

	public CharExpr (StringBuffer character, Position pos) {
        this.character = character.toString();
        this.pos = pos;
        this.e = true;
    }

    public void accept (TreeVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return character;
    }
}