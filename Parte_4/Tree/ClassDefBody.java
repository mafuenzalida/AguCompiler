package Tree;
import java.util.ArrayList;
import Parser.Position;

public class ClassDefBody extends ClassBody
{
	private ArrayList<DeclTree> declarations = null;

	public ClassDefBody (ArrayList<DeclTree> declarations, Position pos) {
        this.declarations = declarations;
        this.pos = pos;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        for(DeclTree dec : declarations) {
            dec.accept (visitor);
        }
        visitor.exit (this);
    }
}