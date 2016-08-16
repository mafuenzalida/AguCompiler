package Tree;
import java.util.ArrayList;

public class ClassDefBody extends ClassBody
{
	private ArrayList<DeclTree> declarations = null;

	public ClassDefBody (ArrayList<DeclTree> declarations) {
        this.declarations = declarations;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        for(DeclTree dec : declarations) {
            dec.accept (visitor);
        }
        visitor.exit (this);
    }
}