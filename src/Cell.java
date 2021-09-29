public class Cell {
    /*
    This class defines a cell that represents a single grid on game board,
    each cell has one symbol that either represent its position (shown to users) or
    the symbol user puts on it.
    */
    private Marker symbol;

    public Cell(){
        symbol = new Marker();
    }
    public Cell(String v){
        symbol = new Marker(v);
    }

    public String get_value(){
        return this.symbol.get_value();
    }

    public void set_value(String v){
        this.symbol.set_value(v);
    }
}
