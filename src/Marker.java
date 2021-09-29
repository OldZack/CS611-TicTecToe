public class Marker {
    /*
    This class defines variables for a cell to contain. For TTT and OAC, the cell only needs one variable.
    If the cell needs more than one variable, a class could be inherited from Maker to generate multiple variables.
    */
    private String value;

    Marker(){
        value = "";
    }

    Marker(String v){
        value = v;
    }

    public String get_value(){
        return value;
    }

    public void set_value(String v){
        this.value = v;
    }
}
