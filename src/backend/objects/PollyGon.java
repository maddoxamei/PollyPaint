package backend.objects;

import backend.Shape;
import processing.core.PApplet;
import processing.core.PVector;
import processing.core.PConstants;
import processing.core.PShape;

import java.io.Serializable;
import java.util.ArrayList;


class PollyGon extends Shape implements Serializable {
  private static final long serialVersionUID = 13L;
  private ArrayList<float[]> points = new ArrayList<float[]>();

  PollyGon(PApplet sketch, float x, float y, ArrayList<float[]> points, float strokeWeight, int[] fillColor, int[] boarderColor){
    super(sketch, x, y, strokeWeight, fillColor, boarderColor);
    for(float[] point : points){
      //point[0] -= xpos;
      //point[1] -= ypos;
      this.points.add(new float[]{point[0], point[1]});
      System.out.println(point[0]+" : "+point[1]);
    }
    createShape();
    setFillColor(255,255,255,0);
    setSettings();
  }

  protected void init(PApplet sketch){
    super.init(sketch);
    for (float[] point : points) {
      point[0] += xpos;
      point[1] += ypos;
    }
    createShape();
    setSettings();
  }

  protected void createShape(){
    float xmin = Float.MAX_VALUE, ymin = Float.MAX_VALUE, xmax = Float.MIN_VALUE, ymax = Float.MIN_VALUE;
    shape = sketch.createShape();
    shape.beginShape();
    this.points = (ArrayList<float[]>) this.points.clone();
    for(int i = 0; i<points.size(); i++){
      xmin = Math.min(points.get(i)[0], xmin);
      ymin = Math.min(points.get(i)[1], ymin);
      xmax = Math.max(points.get(i)[0], xmax);
      ymax = Math.max(points.get(i)[1], ymax);
      points.get(i)[0] -= xpos;
      points.get(i)[1] -= ypos;
      shape.vertex(points.get(i)[0], points.get(i)[1]);
    }

    if(points.size()>2) shape.endShape(PConstants.CLOSE);
    else shape.endShape();
    pixelWidth = Math.abs(xmax - xmin);
    pixelHeight = Math.abs(ymax - ymin);
    xcenter = (xmin + xmax)/2;
    ycenter = (ymin + ymax)/2;
  }

  /*
  protected void createShape(ArrayList<float[]> points){
    float xmin = Float.MAX_VALUE, ymin = Float.MAX_VALUE, xmax = Float.MIN_VALUE, ymax = Float.MIN_VALUE;

    for(int i = 0; i<points.size(); i++){
      float[] pos = new float[]{points.get(i)[0], points.get(i)[1]};
      xmin = Math.min(pos[0], xmin);
      ymin = Math.min(pos[1], ymin);
      xmax = Math.max(pos[0], xmax);
      ymax = Math.max(pos[1], ymax);
    }

    pixelWidth = Math.abs(xmax - xmin);
    pixelHeight = Math.abs(ymax - ymin);
    xcenter = (xmin + pixelWidth)/2;
    ycenter = (ymin + pixelHeight)/2;

    shape.beginShape();
    for(int i = 0; i<points.size(); i++){
      float[] pos = new float[]{points.get(i)[0], points.get(i)[1]};
      shape.vertex(pos[0]-xcenter, pos[1]-ycenter);
    }
    if(points.size()>2) shape.endShape(PConstants.CLOSE);
    else shape.endShape();
  }
  */
/*
  protected boolean withinScope(float x, float y) {
    boolean yes = super.withinScope(x, y);
    System.out.println(shape.contains(-xcenter+xpos, -ycenter+ypos));
    return yes;
  }
*/
  protected void display() {
      super.display();
      sketch.translate(-xcenter+xpos, -ycenter+ypos);
      sketch.shape(shape);
   }

}
