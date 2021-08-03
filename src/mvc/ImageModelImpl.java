package mvc;


import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JLabel;
import javax.swing.JPanel;
import images.DmcColor;


/**
* Concrete class ImageModelImpl that implements the
* interface ImageModel as the model for MVC.
* @author luzixiao.
*/
public class ImageModelImpl implements ImageModel {
  
  /**
  * All the private fields.
  * image:   3d integer array represents the image.
  * dmcChart:  map all 489 dmc colors and its index from
  *            the csv file.
  * dmcLegend: a list contains all the current in use dmc.
  * chunkNum:  number for pixelation.
  * commandMap:  a hash map contains all the supported commands.
  */
  
  private int[][][] image;
  private Map<Integer, DmcColor> dmcChart;
  private List<DmcColor> dmcLegend;
  private JPanel[][] panels;
  private int chunkNum;
  private Map<String, Command> commandMap;
  
  /**
  * Constructor for the class ImageModelImpl.
  * @param image   the image in integer 3d array.
  */
  public ImageModelImpl(int[][][] image) {
    if (image == null) {
      throw new IllegalArgumentException();
    }
    this.image = image;

    this.dmcChart = new HashMap<Integer, DmcColor>();
    this.dmcLegend = new ArrayList<DmcColor>();
    this.commandMap = new HashMap<String, Command>();
    this.panels = null;

  }
  
  /**
  * The default constructor for class ImageModelImpl.
  * This constructor will set the image array to null,
  * which means currently no image file has been loaded 
  * yet.
  */
  
  public ImageModelImpl() {
    this.image = null;
    this.dmcChart = new HashMap<Integer, DmcColor>();
    this.dmcLegend = new ArrayList<DmcColor>();
    this.commandMap = new HashMap<String, Command>();
    this.commandMap.put("load", new LoadImageCommand());
    this.commandMap.put("save", new SaveCommand());
    this.commandMap.put("blur", new BlurCommand());
    this.commandMap.put("sharpening", new SharpeningCommand());
    this.commandMap.put("greyscale", new GreyScaleCommand());
    this.commandMap.put("sepiatone", new SepiaToneCommand());
    this.commandMap.put("reduce", new ReduceColorDensityCommand());
    this.commandMap.put("mosaic", new MosaicCommand());
    this.commandMap.put("pixelation", new PixelationCommand());
    this.commandMap.put("pattern", new GeneratePatternCommand()); 

  }

 

  @Override
  public void setImage(int[][][] image) {
    this.image = image;
    
  }

  @Override
  public int[][][] getImage() {
    if (this.image == null) {
      return null;
    }
    int[][][] result = new int[this.image.length][this.image[0].length][this.image[0][0].length];
    for (int i = 0; i < this.image[0][0].length; i++) {
      for (int j = 0; j < this.image.length; j++) {
        for (int k = 0; k < this.image[0].length; k++) {
          result[j][k][i] = this.image[j][k][i];
        }
      }
    }
    return result;   
  }
  
  
  @Override
  public Map<Integer, DmcColor> getDmcChart() {
    Map<Integer, DmcColor> result = this.dmcChart.entrySet()
        .stream()
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    return result;
  }

  @Override
  public void setDmcChart(Map<Integer, DmcColor> dmcChart) {
    this.dmcChart = dmcChart;  
  }


  //
  @Override
  public List<DmcColor> getDmcLegend() {
    List<DmcColor> result = new ArrayList<DmcColor>();
    for (int i = 0; i < this.dmcLegend.size(); i++) {
      result.add(this.dmcLegend.get(i));
    }
    return result;
  }

  @Override
  public void setDmcLegend(List<DmcColor> newLegend) {
    this.dmcLegend = newLegend;   
  }
  


  @Override
  public void setPanelGrid(JPanel[][] panels) {
    this.panels = panels;
    
  }

  @Override
  public JPanel[][] getPanelGrid() {
    JPanel[][] result = new JPanel[this.panels.length][this.panels[0].length];
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[0].length; j++) {
        result[i][j] = this.panels[i][j];
      }     
    }
    return result;
  }

  @Override
  public void setPanelsAtPisition(int row, int col, JPanel newPanel) {
    this.panels[row][col] = newPanel;
    
  }
  
  //
  @Override
  public JPanel getPanelAtPosition(int row, int col) {
    return this.panels[row][col];
  }

  @Override
  public void setBackGroundColorAtPosition(int row, int col, Color newColor) {
    ((JLabel) this.panels[row][col].getComponent(0)).setText("");
    this.panels[row][col].setBackground(newColor);
  }
  
  @Override
  public void markDiscardArea(int row, int col) {
    ((JLabel) this.panels[row][col].getComponent(0)).setText("*");  
  }

  @Override
  public void setChunkNumber(int chunks) {
    this.chunkNum = chunks;    
  }

  @Override
  public int getChunkNumber() {
    return this.chunkNum;
  }


  @Override
  public Map<String, Command> getCommandMap() {
    return this.commandMap;
  }

}
