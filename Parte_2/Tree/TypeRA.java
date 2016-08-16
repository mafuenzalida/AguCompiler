package Tree;

public class TypeRA extends TypeTree
{
    private TypeTree typel;
    private TypeTree typer;

    public TypeRA (TypeTree typel, TypeTree typer) {
        this.typel = typel;
        this.typer = typer;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        typel.accept (visitor);
        typer.accept (visitor);
        visitor.exit (this);
    }
}
