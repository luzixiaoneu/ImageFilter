package images;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
* The concrete class Mosaic for mosaic operations. 
* @author luzixiao.
*/

public class Mosaic extends AbstractImageProcesser implements ImageMosaic {
 
  /**
  * All the private data members that will be used in this class.
  * seedList: a list contains all the seeds in integer.
  */
  
  public List<List<Integer>> seedList;
  
  /**
  * The constructor for concrete class Mosaic. This constructor will
  * invoke its parent AbstractImageProcesser's first constructor.
  * @param fileName   the input file name. 
  * @throws IOException  will throw an IOException if the file is not in
  *                      the current directory.
  */
  
  public Mosaic(String fileName) throws IOException {
    super(fileName);
    this.seedList = new ArrayList<List<Integer>>();
  }
  
  /**
  * The constructor for concrete class Mosaic. This constructor will
  * invoke its parent AbstractImageProcesser's second constructor.
  * @param image  the 3d integer array that contains the image. 
  */
  public Mosaic(int[][][] image) {
    super(image);
    this.seedList = new ArrayList<List<Integer>>();
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

  @Override
  public void  generateRandomPixels(int numSeeds) {
    Random random = new Random();
    Set<List<Integer>> result = new HashSet<List<Integer>>();
    while (result.size() < numSeeds) {
      int row = random.nextInt((this.height - 1) - 0 + 1) + 0;
      int col = random.nextInt((this.width - 1) - 0 + 1) + 0;
      List<Integer> temp = new ArrayList<>();
      temp.add(row);
      temp.add(col);
      if (!result.contains(temp)) {
        result.add(temp);
        this.seedList.add(temp);
      }
    }  
  }

  @Override
  public List<Integer> findCloestPixel(int row, int col) {
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException();
    }
    int minIndex = 0;
    double min = Double.MAX_VALUE;
    for (int i = 0; i < this.seedList.size(); i++) {
      double rowDifference = (double) row - seedList.get(i).get(0);
      double colDifference = (double) col - seedList.get(i).get(1);
      double distance = Math.sqrt(rowDifference * rowDifference + colDifference * colDifference);
      if (distance < min) {
        min = distance;
        minIndex = i;
      }
    }
    List<Integer> result = new ArrayList<Integer>();
    result.add(this.seedList.get(minIndex).get(0));
    result.add(this.seedList.get(minIndex).get(1));
    return result;
  }

  @Override
  public int[][][] applyMosaic(int numSeeds) {
    if (numSeeds < 0) {
      throw new IllegalArgumentException();
    }
    int[][][] result = this.imageCopy();
    this.generateRandomPixels(numSeeds);
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        for (int k = 0; k < this.chanel; k++) {
          List<Integer> temp = this.findCloestPixel(i, j);
          result[i][j][k] = this.image[temp.get(0)][temp.get(1)][k];
        }
      }
    }
    return result;
    
  }





}
