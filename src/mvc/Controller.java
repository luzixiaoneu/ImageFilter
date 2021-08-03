package mvc;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import images.ColorReduce;
import images.ColorTransformation;
import images.DmcColor;
import images.Filtering;
import images.ImageColorTransformation;
import images.ImageFiltering;
import images.ImageMosaic;
import images.ImagePixelation;
import images.ImageProcesser;
import images.ImageReduceColorDensity;
import images.ImageUtilities;
import images.Mosaic;
import images.Pixelation;


/**
* The concrete implementation of Controller that implements the
* Feature interface.
* @author luzixiao.
*/
public class Controller implements Feature {
  
  /**
  * All private fields that will be used in this class.
  * view:    the view of th MVC.
  * model:   the model of the MVC.
  */
  
  private IView view;
  private ImageModel model;
  
  /**
  * Constructor for Controller class. It takes two
  * parameter, view represents the View in the MVC 
  * and model represents the Model in the MVC.
  * We will allow view to be null when using the script 
  * mode.
  * @param view     the View part of the MVC.
  * @param model    the Model part of MVC.
  */
  
  public Controller(IView view, ImageModel model) {
    if (model == null) {
      throw new IllegalArgumentException();
    }
    this.view = view;
    this.model = model;
  }

  public void start() throws IOException {
    this.view.setOperations(this);
  }
   
  @Override
  public void loadImage(String fileName) throws IOException {
    if (fileName == null) {
      view.showMessageDialog("Please enter the path for the image!");
      return;
    }
    int[][][] temp = ImageUtilities.readImage(fileName);
    this.model.setImage(temp);    
  }

  @Override
  public BufferedImage getImage() {
    if (this.model.getImage() == null) {
      return null;
    }
    int[][][] temp = this.model.getImage();
    return ImageUtilities.getBufferedImage(temp, temp[0].length, temp.length);
  }

  @Override
  public void sharpening() {    
    ImageFiltering sharpen = new Filtering(this.model.getImage());
    this.model.setImage(sharpen.imageBlur(ImageProcesser.sharpening));  
  }


  @Override
  public void blur() {
    if (this.model.getImage() == null) {
      return;
    }
    ImageFiltering blur = new Filtering(this.model.getImage());
    this.model.setImage(blur.imageBlur(ImageProcesser.blur));
    
  }

  @Override
  public void greyScale() {
    int[][][] temp = this.model.getImage();
    ImageColorTransformation greyScale = new ColorTransformation(temp);
    int[][][] result = greyScale.transferColor(ImageProcesser.greyScale);
    this.model.setImage(result);   
  }

  @Override
  public void sepiaTone() {
    ImageColorTransformation sepia = new ColorTransformation(this.model.getImage());
    this.model.setImage(sepia.transferColor(ImageProcesser.sepiaTone));   
  }


  @Override
  public void reduceColorDensity(int colorPerChannel) {
    ImageReduceColorDensity reduce = new ColorReduce(this.model.getImage());
    this.model.setImage(reduce.imageReduce(colorPerChannel));
    
  }


  @Override
  public void mosaic(int seedNum) {
    if (seedNum <= 0) {
      view.popUpWindow("Seed Number Must Be Greater than Zero!");
      return;
    }
    ImageMosaic mosaic = new Mosaic(this.model.getImage());
    this.model.setImage(mosaic.applyMosaic(seedNum));
  }


  @Override
  public void pixelation(int chunkNum) {
    if (chunkNum <= 0) {
      view.showMessageDialog("Chunk Number Must Be Greater than Zero!");
      return;
    }
    ImagePixelation pixelation = new Pixelation(this.model.getImage());
    this.model.setImage(pixelation.pixelation(chunkNum));
  }
  
  @Override
  public void drawPatternForScript(String fileName) throws IOException {
    if (fileName == null) {
      this.view.showMessageDialog("Please enter the file Name!");
      return;
    }
    ImagePixelation pixelation = new Pixelation(this.model.getImage());
    pixelation.drawPattern(100, fileName);
    
  }
  
  
  @Override
  public JScrollPane createGridView(int chunkNum) throws IOException {
    this.model.setChunkNumber(chunkNum);
    ImagePixelation pixelation = new Pixelation(this.model.getImage());    
    int[][][] processed = pixelation.stitchPatternForUi(chunkNum);
    int width = processed[0].length;
    int height = processed.length;
    JPanel main = new JPanel(new GridLayout(height, width, 1, 1));
    this.model.setPanelGrid(new JPanel[height][width]);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int red = processed[i][j][0];
        int green = processed[i][j][1];
        int blue = processed[i][j][2]; 
        JPanel tmp = new JPanel(); 
        tmp.setSize(5, 5);
        JLabel label = new JLabel(" ");
        tmp.add(label);
        tmp.setBackground(new Color(red, green, blue));           
        this.model.setPanelsAtPisition(i, j, tmp);     
        main.add(this.model.getPanelAtPosition(i, j));        
      }
    }
    this.model.setDmcChart(pixelation.getDmcChart());
    this.model.setDmcLegend(pixelation.gettingAllInUseDmcColor());
    return new JScrollPane(main);   
  }

  
  @Override
  public void applyChangeToGrid(Color oldColor, Color newColor) {
    if (oldColor == null || newColor == null) {
      view.showMessageDialog("Error!");
      return;
    }
    int oldRed = oldColor.getRed();
    int oldGreen = oldColor.getGreen();
    int oldBlue = oldColor.getBlue();
    for (int i = 0; i < this.model.getPanelGrid().length; i++) {
      for (int j = 0; j < this.model.getPanelGrid()[0].length; j++) {
        if (this.model.getPanelAtPosition(i, j).getBackground().getRed() == oldRed
            && this.model.getPanelAtPosition(i, j).getBackground().getGreen() == oldGreen
            && this.model.getPanelAtPosition(i, j).getBackground().getBlue() == oldBlue) {
          this.model.setBackGroundColorAtPosition(i, j, newColor);         
        }
      }
    }   
  }
  
  @Override
  public void displayDiscardedPattern(Color colorToDiscard) {
    if (colorToDiscard == null) {
      view.showMessageDialog("Can Not Discard an Empty Color!");
      return;
    }
    int oldRed = colorToDiscard.getRed();
    int oldGreen = colorToDiscard.getGreen();
    int oldBlue = colorToDiscard.getBlue();
    for (int i = 0; i < this.model.getPanelGrid().length; i++) {
      for (int j = 0; j < this.model.getPanelGrid()[0].length; j++) {
        if (this.model.getPanelAtPosition(i, j).getBackground().getRed() == oldRed
            && this.model.getPanelAtPosition(i, j).getBackground().getGreen() == oldGreen
            && this.model.getPanelAtPosition(i, j).getBackground().getBlue() == oldBlue) {
          this.model.markDiscardArea(i, j);         
        }
      }
    }  
  }

  @Override
  public List<DmcColor> getLegendInUse() {
    return this.model.getDmcLegend();
  }
  
  @Override
  public Map<Integer, DmcColor> getDmcChart() {
    return this.model.getDmcChart();
  }

  @Override
  public void setImageAfterStitchPattern() {
    ImagePixelation pixelation = new Pixelation(this.model.getImage());     
    this.model.setImage(pixelation.generateImageAfterStitchingPattern(this.model.getChunkNumber(), 
        this.model.getPanelGrid()));
  }

  @Override
  public void saveImage(String fileName) throws IOException {
    ImageUtilities.writeImage(this.model.getImage(), this.model.getImage()[0].length, 
        this.model.getImage().length, fileName);
    
  }

  @Override
  public void drawPatternForUI(String path) throws IOException {
    String[] pattern = this.getPatternInString();

    BufferedWriter output = new BufferedWriter(new FileWriter(path)); 
    for (int i = 0; i < pattern.length; i++) {
      output.write(pattern[i] + "\n");
    }     

    String[] legend = this.getLegendInString();
    for (int i = 0; i < legend.length; i++) {
      output.write(legend[i] + "\n");
    }
    output.flush();
    output.close();


  }
  
  /**
  * a private helper method that will get the current pattern
  * generated in string.
  * @return    a string array represent the current pattern
  *            that will be write to the text file.
  */
  
  private String[] getPatternInString() {
    String[] result = new String[this.model.getPanelGrid().length];  
    for (int i = 0; i < this.model.getPanelGrid().length; i++) {
      StringBuilder sb = new StringBuilder();
      for (int j = 0; j < this.model.getPanelGrid()[0].length; j++) {
        Color tmpColor = this.model.getPanelAtPosition(i, j).getBackground();
        for (int k = 0; k < this.model.getDmcLegend().size(); k++) {        
          if (tmpColor.getRed() == this.getLegendInUse().get(k).getRed()
              && tmpColor.getGreen() == this.getLegendInUse().get(k).getGreen()
              && tmpColor.getBlue() == this.getLegendInUse().get(k).getBlue()) {
            sb.append(this.getLegendInUse().get(k).getSymbol());
          }
        }
      }
      result[i] = sb.toString();
    }
    return result;
  }
  /**
  * A private helper method that will get the current dmc legend
  * as a string array.
  * @return    a string array that represents the current dmc legend.
  */
  
  private String[] getLegendInString() {
    int size = this.model.getDmcLegend().size();
    String[] result = new String[size];
    for (int i = 0; i < size; i++) {
      String id = this.model.getDmcLegend().get(i).getId();
      String symbol = this.model.getDmcChart().get(i).getSymbol();
      result[i] = "DMC-" + id + " Symbol-" + symbol;
    }
    return result;
  }

  @Override
  public int findIndexFromLegendInUse(JButton oldColor) {
    if (oldColor == null) {
      view.showMessageDialog("Please Select an valid button!");
      return -1;
    }
    for (int i = 0; i < this.model.getDmcLegend().size(); i++) {
      Color old =  oldColor.getBackground();
      if (old.getRed() == this.model.getDmcLegend().get(i).getRed() 
          && old.getGreen() == this.model.getDmcLegend().get(i).getGreen() 
          && old.getBlue() == this.model.getDmcLegend().get(i).getBlue()) {
        return i;
      }     
    }   
    return -1;
  }

  @Override
  public void setDmcLegend(List<DmcColor> newLegend) {
    if (newLegend == null) {
      view.showMessageDialog("Error Updating Legend!");
      return;
    }
    this.model.setDmcLegend(newLegend);
    
  }

  @Override
  public void executeScript(JTextArea textField) throws IOException {
    if (textField == null) {
      view.showMessageDialog("Error Creating Script");
      return;
    }
    String content = textField.getText();
    String[] contentLineByLine = content.split("\n");
    for (int i = 0; i < contentLineByLine.length; i++) {
      String[] currentCommandString = contentLineByLine[i].split(" ");
      Command currentCommand = 
          this.model.getCommandMap().getOrDefault(currentCommandString[0], null);
      if (currentCommand == null) {
        this.view.showMessageDialog(currentCommandString[0] + " is not supported!");
        return;
      }
      else {
        currentCommand.execute(currentCommandString, this);
      }
    }
    this.model.setImage(null);
    
  }

  /**
  * This method will be used to execute script in the script mode.
  * @param currentCommand    all the commands in the scripts.
  * @throws IOException      will thorw an IOException if user in the
  *                          script trying to write or load to an invalid
  *                          path.
  */
  public void executeScriptForScriptMode(String[] currentCommand) throws IOException {
    if (currentCommand == null) {
      throw new IllegalArgumentException();
    }
    Command temp = this.model.getCommandMap().getOrDefault(currentCommand[0], null);
    if (temp == null) {
      throw new IllegalArgumentException(currentCommand[0]);
    }   
    temp.execute(currentCommand, this);
    
  }




  
  



}
 