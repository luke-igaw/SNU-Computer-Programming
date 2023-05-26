import javax.swing.SingleSelectionModel;

class Shape {
    protected int offset_x, offset_y;
    public Shape(int offset_x, int offset_y){
        this.offset_x = offset_x;
        this.offset_y = offset_y;
    }
}

public class Rectangle extends Shape{
    public void pirntRectangle(){
        System.out.println("Rectangle");
    }
    protected int offset_x = 10, offset_y = 10;
    public Rectangle(){
        super(0,0);
        System.out.println(super.offset_x);
    }
    public static void main(String args[]){
        Rectangle r = new Rectangle();
        System.out.println(r.offset_x);
        new Rectangle().pirntRectangle();
    }
}
