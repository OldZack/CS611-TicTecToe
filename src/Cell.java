public class Cell {
    private String value;

    public Cell(){
        value = "";
    }
    public Cell(String v){
        this.value = v;
    }

    public String get_value(){
        return this.value;
    }

    public void set_value(String v){
        this.value = v;
    }
}
