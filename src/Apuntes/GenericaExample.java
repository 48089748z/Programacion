package Apuntes;
public class GenericaExample <Anything>
{
    private Anything variable;
    public GenericaExample(){}
    public GenericaExample(Anything variable) {this.variable=variable;}
    public Anything getVariable() {return variable;}
    public void setVariable(Anything variable) {this.variable = variable;}
}
