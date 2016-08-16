package Tree;

public class CharExpr extends ExprTree
{
    private String character;

	public CharExpr (StringBuffer character) {
        this.character = character.toString();
    }

    public void accept (TreeVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return character;
    }
}