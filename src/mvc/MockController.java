package mvc;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import images.DmcColor;

/**
* A concrete implementation MockController that implements 
* the interface Feature. This class is used to mock the behavior
* of the actual controller in the program. For testing purpose only!
* @author luzixiao.
*/

public class MockController implements Feature {

  /**
  * private fields that will be used in the class.
  * model:    the mock model for testing.
  * sb:       a string builder that records the operations.
  */
  
  private MockImageModel model;

  private StringBuilder sb;
  
  /**
  * Constructor for MockController.
  * @param model   the mock model to use in test.
  */
  
  public MockController(MockImageModel model) {
    this.model = model;
    this.sb = new StringBuilder();

  }

  @Override
  public void loadImage(String fileName) throws IOException {
    if (fileName == null) {
      throw new IOException();
    }    
    this.model.setImage(new int[1][1][1]);    
    sb.append("load " + fileName + " ");
  }

  @Override
  public void saveImage(String fileName) throws IOException {
    if (fileName == null) {
      throw new IOException();
    }
    if (this.model.getImage() == null) {
      throw new IllegalStateException();
    }
    sb.append("save " + fileName + " ");
  }

  @Override
  public void greyScale() {
    if (this.model.getImage() == null) {
      throw new IllegalStateException();
    }  
    this.model.setImage(new int[1][1][1]);
    sb.append("greyscale ");
    
  }

  @Override
  public void sharpening() {
    if (this.model.getImage() == null) {
      throw new IllegalStateException();
    }  
    this.model.setImage(new int[1][1][1]);  
    sb.append("sharpening ");
  }

  @Override
  public void blur() {
    if (this.model.getImage() == null) {
      throw new IllegalStateException();
    }  
    this.model.setImage(new int[1][1][1]);
    sb.append("blur ");
  }

  @Override
  public void sepiaTone() {
    if (this.model.getImage() == null) {
      throw new IllegalStateException();
    }  
    this.model.setImage(new int[1][1][1]);
    sb.append("sepiatone ");
  }

  @Override
  public void reduceColorDensity(int colorPerChannel) {
    if (colorPerChannel <= 0) {
      throw new IllegalArgumentException();
    }
    if (this.model.getImage() == null) {
      throw new IllegalStateException();
    }
    this.model.setImage(new int[1][1][1]);
    sb.append("reduce " + colorPerChannel + " ");
    
  }

  @Override
  public void mosaic(int seed) {
    if (seed <= 0) {
      throw new IllegalArgumentException();
    }
    if (this.model.getImage() == null) {
      throw new IllegalStateException();
    }
    this.model.setImage(new int[1][1][1]);
    sb.append("mosaic " + seed + " ");
  }

  @Override
  public void pixelation(int chunkNum) {
    if (chunkNum <= 0) {
      throw new IllegalArgumentException();
    }
    if (this.model.getImage() == null) {
      throw new IllegalStateException();
    }
    this.model.setImage(new int[1][1][1]); 
    sb.append("pixelation " + chunkNum + " ");
    
  }

  @Override
  public void drawPatternForScript(String fileName) throws IOException {
    if (fileName == null) {
      throw new IOException();
    }
    sb.append("generate txt file in script mode ");  
  }

  @Override
  public void drawPatternForUI(String path) throws IOException {
    if (path == null) {
      throw new IOException();
    }
    sb.append("generate txt file in UI mode ");
    
  }

  @Override
  public JScrollPane createGridView(int chunkNum) throws IOException {
    if (chunkNum <= 0) {
      throw new IllegalArgumentException();
    }
    if (this.model.getImage() == null) {
      throw new IllegalStateException();
    }
    sb.append("grid view generated ");
    return new JScrollPane();    
  }

  @Override
  public void applyChangeToGrid(Color oldColor, Color newColor) {   
    if (oldColor == null || newColor == null) {
      throw new IllegalArgumentException();
    }
    sb.append("color replaced ");
  }
  
  @Override
  public void setImageAfterStitchPattern() {
    sb.append("displaying image after cross stitch for UI ");
  }

  @Override
  public void setDmcLegend(List<DmcColor> newLegend) {
    sb.append("updating current dmc lengend ");   
  }

  @Override
  public void displayDiscardedPattern(Color colorToDiscard) {
    sb.append("displaying removed color ");
    
  }

  @Override
  public void executeScript(JTextArea textfield) throws IOException {
    if (textfield.getText().equals("")) {
      throw new IOException();
    }
    sb.append("run script ");   
  }
  
  @Override
  public List<DmcColor> getLegendInUse() {    
    return new ArrayList<DmcColor>();
  }

  @Override
  public Map<Integer, DmcColor> getDmcChart() {
    return new HashMap<Integer, DmcColor>();
  }
  
  @Override
  public int findIndexFromLegendInUse(JButton oldColor) {
    return 0;
  }
  
  @Override
  public BufferedImage getImage() {
    return new BufferedImage(1, 1, 1);
  }

  @Override
  public String toString() {
    return sb.toString();
  }
  


}
