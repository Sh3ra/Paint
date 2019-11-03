package eg.edu.alexu.csd.oop.draw;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.HashMap;
import java.util.Map;

public class Square implements Shape  {
    private Point position;
    private Map<String, Double> properties= new HashMap<>();
    private Color color;
    private Color fillColor;
    public Square(){
        position = new Point(0,0);
        color = Color.black;
        fillColor = Color.black;
        this.properties.put("type",5d);
        this.properties.put("released",0d);
        this.properties.putIfAbsent("selected",0d);
        this.properties.putIfAbsent("x2", Double.valueOf(position.x));
        this.properties.putIfAbsent("y2", Double.valueOf(position.y));
    }
    public void setPosition(Point position) {
        this.position=position;
    }

    public Point getPosition() {
        return position;
    }

    public void setProperties(Map<String, Double> properties) {
        this.properties=properties;
        if(this.properties.get("selected")==1d)
        {
            Color temp=fillColor;
            this.fillColor=color;
            this.color=temp;
            this.properties.replace("selected",0d);
        }
    }

    public Map<String, Double> getProperties() {
        return properties;
    }

    public void setColor(Color color) {
        this.color=color;
    }

    public Color getColor() {
        return color;
    }

    public void setFillColor(Color color) {
        this.fillColor=color;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void draw(Graphics canvas) {
        Point p1 = new Point(getProperties().get("x2").intValue(), getProperties().get("y2").intValue());
        Point p3 = Correct(position, p1);
        Double l, w, mn;
        if (p3.x == position.x && p3.y == position.y) {
            l = Double.valueOf(Math.abs(p1.x - position.x));
            w = Double.valueOf(Math.abs(p1.y - position.y));
            mn = Double.valueOf(max(l.intValue(), w.intValue()));
        } else if (p3.x == position.x) {
            mn = Double.valueOf(position.y - p3.y);
        } else if (p3.y == position.y) {
            mn = Double.valueOf(position.x - p3.x);

        } else {
            if (position.x - p3.x < position.y - p3.y) p3.x = position.y - position.x + p3.y;
            if (position.x - p3.x > position.y - p3.y) p3.x = position.x - position.y + p3.y;
            mn = Double.valueOf(position.x - p3.x);

        }
        if (properties.get("released") == 1d) {
            Double j = Double.valueOf(p3.x + mn.intValue());
            properties.put("x2", j);
            j = Double.valueOf(p3.y + mn.intValue());
            properties.put("y2", j);
            properties.put("released", 0d);
            setPosition(p3);
        }
            canvas.setColor(getFillColor());
            canvas.fillRect(p3.x, p3.y, mn.intValue(), mn.intValue());
            canvas.setColor(getColor());
            canvas.drawRect(p3.x, p3.y, mn.intValue(), mn.intValue());

    }

    public Object clone() throws CloneNotSupportedException {
        Square c=new Square();
        c.setProperties(getProperties());
        c.setPosition(getPosition());
        c.setColor(getColor());
        c.setFillColor(getFillColor());
        return c;
    }

    Point Correct(Point p1,Point p2){
        Point p3 = new Point();
        p3.x = min(p1.x,p2.x);
        p3.y = min(p1.y,p2.y);
        return p3;
    }
    int min(int a,int b){
        if(a<b) return a;
        return b;
    }
    int max(int a,int b){
        if(a<b) return b;
        return b;
    }
}
