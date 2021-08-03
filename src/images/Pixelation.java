package images;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JPanel;


/**
* The concrete class Pixelation for pixelation operations. 
* @author luzixiao.
*/

public class Pixelation extends AbstractImageProcesser implements ImagePixelation {

  /**
  * All the private data members that will be used in
  * this class.
  * blockNum:  the number of blocks for super pixels across
  *            the width.
  * blockSize: the size for each super pixel.
  * map:       a hash map that contains the mapping of dmc and label.
  * dmcChart:  a hash map that contains the dmc chart.
  */
  
  private int blockNum;
  private int blockSize;
  private Map<Integer, DmcColor> dmcChart;
  private Set<Integer> allUsedDmcIndex;
  private List<DmcColor> allUsedDmcColrList;
  
  
  /**
  * The constructor for concrete class Pixelation. This constructor will
  * invoke its parent AbstractImageProcesser's first constructor.
  * @param fileName   the input file name. 
  * @throws IOException  will throw an IOException if the file is not in
  *                      the current directory.
  */
  
  public Pixelation(String fileName) throws IOException {
    super(fileName);
    this.blockNum = 0;
    this.blockSize = 0;
    this.dmcChart = new HashMap<Integer, DmcColor>();
    this.allUsedDmcIndex = new HashSet<Integer>();


  }
  
  /**
  * The constructor for concrete class Pixelation. This constructor will
  * invoke its parent AbstractImageProcesser's second constructor.
  * @param image  the 3d integer array that contains the image. 
  */
  public Pixelation(int[][][] image) {
    super(image);
    this.blockNum = 0;
    this.blockSize = 0;
    this.dmcChart = new HashMap<Integer, DmcColor>();
    this.allUsedDmcIndex = new HashSet<Integer>();
    this.allUsedDmcColrList = new ArrayList<DmcColor>();

  }
  
  

  @Override
  public int[][][] pixelation(int blockNum) {    
    if (blockNum <= 0) {
      throw new IllegalArgumentException();
    }
    this.blockNum = blockNum;
    this.blockSize = this.width / blockNum; 
    int widthOverFlow = this.width % blockNum;
    int heightOverFlow = this.height % blockSize;
    int[][][] result = this.imageCopy();  
    Map<Integer, Integer> colMapping = this.mapColumn(widthOverFlow, heightOverFlow);
    Map<Integer, Integer> rowMapping = this.mapRow(heightOverFlow);   
    for (int i = 0; i < this.chanel; i++) {
      int[][] currentLayer = this.getNewSuperPixelBlocks(i, heightOverFlow, widthOverFlow);
      for (int j = 0; j < this.height; j++) {
        for (int k = 0; k < this.width; k++) {
          Integer newRow = Math.min(currentLayer.length - 1, rowMapping.get(j));
          Integer newCol = colMapping.get(k);         
          result[j][k][i] = currentLayer[newRow][newCol];
        }
      }
    }
    return result;
  }
  
  @Override
  public int[][][] generateImageAfterStitchingPattern(int blockNum, JPanel[][] panel) {
    this.blockNum = blockNum;
    this.blockSize = this.width / blockNum;
    int widthOverFlow = this.width % blockNum;
    int heightOverFlow = this.height % blockSize;
    int[][][] result = this.imageCopy();     
    Map<Integer, Integer> colMapping = this.mapColumn(widthOverFlow, heightOverFlow);
    Map<Integer, Integer> rowMapping = this.mapRow(heightOverFlow); 
    for (int i = 0; i < this.chanel; i++) {
      int[][] currentLayer = this.getGridUnitBackGroundColor(i, panel);
      for (int j = 0; j < this.height; j++) {
        for (int k = 0; k < this.width; k++) {
          Integer newRow = Math.min(currentLayer.length - 1, rowMapping.get(j));
          Integer newCol = colMapping.get(k);         
          result[j][k][i] = currentLayer[newRow][newCol];
          
        }
      }
    }
    return result;
  }
  
  private int[][] getGridUnitBackGroundColor(int channel, JPanel[][] panel) {
    int width = panel[0].length;
    int height = panel.length;
    int[][] result = new int[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (channel == 0) {
          result[i][j] = panel[i][j].getBackground().getRed();
        }
        if (channel == 1) {
          result[i][j] = panel[i][j].getBackground().getGreen();
        }
        if (channel == 2) {
          result[i][j] = panel[i][j].getBackground().getBlue();
        }
        
      }
    }
    return result;
  }
  

  @Override
  public int[][] getNewSuperPixelBlocks(int channel, int heightOverFlow, int widthOverFlow) {
    if (channel < 0 || channel > 2) {
      throw new IllegalArgumentException();
    }
    int heightOverFlowRemain = heightOverFlow;
    int rowCount = 0;
    int[][] answer = new int[this.height / this.blockSize][this.blockNum];
    for (int i = 0; i < this.height / this.blockSize; i++) {
      int colCount = 0;
      int widthOverFlowRemain = widthOverFlow;
      int height = this.blockSize;
      if (heightOverFlowRemain > 0) {
        height ++;
        heightOverFlowRemain -= 1;
      }
      for (int j = 0; j < this.blockNum; j++) {
        int width = this.blockSize;      
        if (widthOverFlowRemain > 0) {
          width ++;
          widthOverFlowRemain -= 1;
        }  
        double botRight = (double) image[height / 2 + rowCount][width / 2 + colCount][channel];
        double botLeft = (double) image[height / 2 + rowCount ][width / 2 + colCount - 1][channel];
        double topRight = (double) image[height / 2 + rowCount - 1][width / 2 + colCount][channel];
        double topLeft = (double) image[height / 2 + rowCount - 1]
            [width / 2 + colCount - 1][channel];
        int newValue = (int) (botRight + botLeft + topRight + topLeft) / 4;
        answer[i][j] = newValue;
        colCount += width;
      }
      rowCount += height;
    } 
    return answer;
  }
  
  @Override
  public void drawPattern(int blockNum, String fileName) throws IOException {
    if (blockNum <= 0 || fileName == null) {
      throw new IllegalArgumentException();
    }
    this.blockNum = blockNum;
    this.blockSize = this.width / blockNum; 
    int widthOverFlow = this.width % blockNum;
    int heightOverFlow = this.height % blockSize;  
    this.dmcChart = this.readFromTable("dmc.csv");
    int[][] redLayer = this.getNewSuperPixelBlocks(0, heightOverFlow, widthOverFlow);
    int[][] greenLayer = this.getNewSuperPixelBlocks(1, heightOverFlow, widthOverFlow);
    int[][] blueLayer = this.getNewSuperPixelBlocks(2, heightOverFlow, widthOverFlow);
    String[] result = this.stitch(blockNum, redLayer, greenLayer, blueLayer);
    String legend = this.generateLegend();
    String sizeChart = this.generateSizeChart();
    this.writeToTxt(fileName, result, legend, sizeChart);
    
  }
  
  /**
  * Map the image's every pixel's horizontal corrdinate to the corresponding 
  * super pixel's horizontal corrdinate. 
  * @param widthOverFlow   the overflow part from the width.
  * @param heightOverFlow  the overflow part from the hegith.
  * @return  a has map that mapps every pixel's horizontal corridinate to the 
  *          corresponding super pixel's horizontal corridinate. 
  */
  private Map<Integer, Integer> mapColumn(int widthOverFlow, int heightOverFlow) {
    Map<Integer, Integer> colToCol = new HashMap<Integer, Integer>();
    int width = this.blockSize;
    int widthOverFlowRemain = widthOverFlow;
    int colCounter = 0;
    for (int i = 0; i < this.width; i += width) { 
      width = this.blockSize;
      if (widthOverFlowRemain > 0) {
        width += 1;
        widthOverFlowRemain --;
      }
      for (int j = 0; j < width; j++) {
        colToCol.put(i + j, colCounter);     
      }
      colCounter++;
    }
    return colToCol;   
  }

  /**
  * Map the image's every pixel's vertical corrdinate to the corresponding 
  * super pixel's vertical corrdinate. 
  * @param widthOverFlow   the overflow part from the width.
  * @param heightOverFlow  the overflow part from the hegith.
  * @return  a has map that mapps every pixel's vertical corridinate to the 
  *          corresponding super pixel's vertical corridinate. 
  */
  
  private Map<Integer, Integer> mapRow(int heightOverFlow) {
    Map<Integer, Integer> rowToRow = new HashMap<Integer, Integer>();
    int height = this.blockSize;   
    int heightOverFlowRemain = heightOverFlow;
    int rowCounter = 0;
    for (int i = 0; i < this.height; i += height) {
      height = this.blockSize;
      if (heightOverFlowRemain > 0) {
        height ++;
        heightOverFlowRemain --;
      }
      for (int j = 0; j < height; j++) {
        rowToRow.put(i + j, rowCounter);
      }
      rowCounter++;
    }     
    return rowToRow;
  }


  /**
   * A private helper method that makes a copy
   * of the 3d array image into a same 3d integer
   * array.
   * @return   the copy of the image array.
   */

  private int[][][] imageCopy() {
    int[][][] result = new int[this.height][this.width][this.chanel];
    for (int i = 0; i < this.chanel; i++) {
      for (int j = 0; j < this.height; j++) {
        for (int k = 0; k < this.width; k++) {
          result[j][k][i] = image[j][k][i];
        }
      }
    }
    return result;
  }


  /**
  * This method read the DMC chart from the file and 
  * return it as a hash map.
  * @param fileName  the file name for the dmc chart.
  * @return a hash map contains the index and all the other
  *         info for every dmc in the table.
   * @throws IOException    will throw IOException if the path 
   *                        is not valid.
  */
  
  private Map<Integer, DmcColor> readFromTable(String fileName) throws IOException {
    if (fileName == null) {
      throw new IllegalArgumentException();
    }
    FileReader file = new FileReader(fileName, StandardCharsets.UTF_8);

    BufferedReader reader = new BufferedReader(file);
    Map<Integer, DmcColor> result = new HashMap<Integer, DmcColor>();
    int index = 0;
    int unicodeIndex = 50; 
    String tmp;
    while ((tmp = reader.readLine()) != null) {
      Character label = (char) unicodeIndex;
      String[] currentLine = tmp.split(",");
      DmcColor currentDmc = new DmcColor(currentLine[0], currentLine[1], 
          Integer.parseInt(currentLine[2]), 
          Integer.parseInt(currentLine[3]), Integer.parseInt(currentLine[4]), 
          Character.toString(label), index);
      result.put(index, currentDmc);   
      index ++;
      unicodeIndex ++;
    }
    reader.close();
    return result;
  }
  
  
  




  /**
  * This method will do the calculation for the stitch patter
  * and generate the pattern result in a string array.
  * @param blockNum   the number of block for pixelation.
  * @return  a string array that contains the pattern.
  * @throws FileNotFoundException  will throw a FileNotFoundException
  *         if dmc.csv file is not in the directory.
  */
  
  private String[] stitch(int blockNum, int[][]redLayer, 
      int[][] greenLayer, int[][] blueLayer) throws IOException {
    
    if (blockNum <= 0) {
      throw new IllegalArgumentException();
    }
    this.blockNum = blockNum;
    this.blockSize = this.width / blockNum;
    String[] result = new String[this.height / this.blockSize];    
    this.dmcChart = this.readFromTable("dmc.csv");
    int size = this.dmcChart.size();
    for (int i = 0; i < this.height / this.blockSize; i++) {
      StringBuilder sb = new StringBuilder();
      for (int j = 0; j < this.blockNum; j++) {
        int smallest = Integer.MAX_VALUE;
        int smallestIndex = 0;
        for (int k = 0; k < size; k++) {
          DmcColor rgb = this.dmcChart.get(k);
          int r = (redLayer[i][j] + rgb.getRed()) / 2;
          int redDifference = redLayer[i][j] - rgb.getRed();
          int greenDifference = greenLayer[i][j] - rgb.getGreen();
          int blueDifference = blueLayer[i][j] - rgb.getBlue();          
          int minDistance = (int)(Math.sqrt((2 + r / 256) * redDifference * redDifference 
              + 4 * greenDifference * greenDifference
              + (2 + (255 - r) / 256) * blueDifference * blueDifference));
          if (minDistance <= smallest) {
            smallest = minDistance;
            smallestIndex = k;
          }              
        }
        this.allUsedDmcIndex.add(smallestIndex);
        sb.append(this.dmcChart.get(smallestIndex).getSymbol());

      } 
      result[i] = sb.toString();

    }     
    return result;
  }
  
  
  @Override
  public int[][][] stitchPatternForUi(int blockNum) throws IOException {
    if (blockNum <= 0) {
      throw new IllegalArgumentException();
    }
    this.blockNum = blockNum;
    this.blockSize = this.width / blockNum;  
    int[][][] result = new int[this.height / this.blockSize][this.blockNum][this.chanel];
    int widthOverFlow = this.width % blockNum;
    int heightOverFlow = this.height % blockSize;      
    int[][] redLayer = this.getNewSuperPixelBlocks(0, heightOverFlow, widthOverFlow);
    int[][] greenLayer = this.getNewSuperPixelBlocks(1, heightOverFlow, widthOverFlow);
    int[][] blueLayer = this.getNewSuperPixelBlocks(2, heightOverFlow, widthOverFlow);
    this.dmcChart = this.readFromTable("dmc.csv");
    int size = this.dmcChart.size();
    for (int i = 0; i < this.height / this.blockSize; i++) {
      for (int j = 0; j < this.blockNum; j++) {
        double smallest = Double.MAX_VALUE;
        int smallestIndex = 0;
        for (int k = 0; k < size; k++) {
          DmcColor rgb = this.dmcChart.get(k);

          double r = (redLayer[i][j] + rgb.getRed()) / 2;
          double redDifference = redLayer[i][j] - rgb.getRed();
          double greenDifference = greenLayer[i][j] - rgb.getGreen();
          double blueDifference = blueLayer[i][j] - rgb.getBlue();          
          int minDistance = (int)(Math.sqrt((2 + r / 256) * redDifference * redDifference 
              + 4 * greenDifference * greenDifference
              + (2 + (255 - r) / 256) * blueDifference * blueDifference));
          if (minDistance <= smallest) {
            smallest = minDistance;
            smallestIndex = k;
          }              
        }          
        this.allUsedDmcIndex.add(smallestIndex);
        result[i][j][0] = this.dmcChart.get(smallestIndex).getRed();
        result[i][j][1] = this.dmcChart.get(smallestIndex).getGreen();
        result[i][j][2] = this.dmcChart.get(smallestIndex).getBlue();
      }     
    }
    return result;
  }
  
  

  /**
  * This method will generate the size chart.
  * @return  a string represent the size chart.
  */
  
  private String generateSizeChart() {
    return this.blockNum + " X " + this.height / this.blockSize;
  }
  
  /**
  * This method will generate the legend chart.
  * @return  a string represent the legend chart.
  */
  private String generateLegend() {
    String result = "";
    for (Integer index : this.allUsedDmcIndex) {
      String temp = "DMC-" + this.dmcChart.get(index).getId() 
          + " Symbol-" + this.dmcChart.get(index).getSymbol() + "\n";
      result += temp;
    }
    return result;
  }

  

  /**
  * This method will write the pattern, size chart and legend chart
  * into an output txt file.
  * @param fileName  name of the output file.
  * @param pattern   the pattern in string array.
  * @param legend    the legend chart in string.
  * @param sizeChart  the size chart in string.
  * @throws IOException  will throw an IOException if the
  *                      output file name is invalid.
  */
  
  private void writeToTxt(String fileName, String[] pattern, 
      String legend, String sizeChart) throws IOException {
    if (fileName == null || pattern == null || legend == null || sizeChart == null) {
      throw new IllegalArgumentException();
    }
    BufferedWriter output = new BufferedWriter(new FileWriter(fileName));
    output.write(sizeChart + "\n");
    for (int i = 0; i < pattern.length; i++) {
      output.write(pattern[i] + "\n");
    }
    output.write(legend);
    output.flush();
    output.close();
  }


  
  @Override
  public Map<Integer, DmcColor> getDmcChart() {
    return this.dmcChart;
  }
  

  @Override
  public List<DmcColor> gettingAllInUseDmcColor() {
    for (Integer index : this.allUsedDmcIndex) {
      DmcColor tmp = this.dmcChart.get(index);
      this.allUsedDmcColrList.add(tmp);
    }
    return this.allUsedDmcColrList;
  }










  

  

  
}
