package Tree;
import Parser.Position;

public class TypeRA extends TypeTree
{
    public TypeTree typel;
    public TypeTree typer;

    public TypeRA (TypeTree typel, TypeTree typer, Position pos) {
        this.typel = typel;
        this.typer = typer;
        this.pos = pos;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        typel.accept (visitor);
        typer.accept (visitor);
        visitor.exit (this);
    }
}
