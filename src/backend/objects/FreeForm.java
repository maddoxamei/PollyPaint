package backend.objects;
import backend.Shape;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import java.util.ArrayList;

class FreeForm extends Shape {
  FreeForm(PApplet sketch, float x, float y, ArrayList<PVector> points, float strokeWeight, int[] fillColor, int[] boarderColor){
    super(sketch, x, y, strokeWeight, fillColor, boarderColor);
    createShape(points);
    setSettings();
    this.pixelWidth = shape.width;
    this.pixelHeight = shape.height;
  }

  protected void createShape(ArrayList<PVector> points){
  	//shape = createShape();
    sketch.noFill();
    shape.beginShape();
    for(int i = 0; i<points.size(); i++){
      shape.vertex(points.get(i).x, points.get(i).y);
    }
    shape.endShape();
  }

}