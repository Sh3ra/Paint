package eg.edu.alexu.csd.oop.draw;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.HashMap;
import java.util.Map;

public class Rectangle implements Shape  {
    private Point position;
    private Map<String, Double> properties= new HashMap<>();
    private Color color;
    private Color fillColor;
    public Rectangle(){
        position = new Point(0,0);
        color = Color.black;
        fillColor = Color.black;
        this.properties.put("type",3d);
        this.properties.put("released",1d);
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
        this.properties.putIfAbsent("selected", 0.0D);
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
        Point p1 = new Point(getProperties().get("x2").intValue(),getProperties().get("y2").intValue());
        Point p3 = Correct(position,p1);
        Double l = Double.valueOf(Math.abs(p1.x-position.x));
        Double w = Double.valueOf(Math.abs(p1.y-position.y));
        canvas.setColor(getFillColor());
        canvas.fillRect(p3.x,p3.y,l.intValue(),w.intValue());
        canvas.setColor(getColor());
        canvas.drawRect(p3.x,p3.y,l.intValue(),w.intValue());
    }

    public Object clone() throws CloneNotSupportedException {
        Rectangle c=new Rectangle();
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
}