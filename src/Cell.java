public class Cell {
    private String value;
    private int row;
    private int col;

    public Cell(){
        value = "";
        row = -1;
        col = -1;
    }
    public Cell(String v, int r, int c){
        this.value = v;
        this.row = r;
        this.col = c;
    }

    public String get_value(){
        return this.value;
    }

    public void set_value(String v){
        this.value = v;
    }
}
