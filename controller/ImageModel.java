package controller;

import java.io.IOException;

/**
* The ImageModel interface that represent
* the type ImageModel which stores the image.
* @author luzixiao.
*/
public interface ImageModel {

  /**
  * This method will apply the blur filter
  * to the image. 
  */
  void applyBlur();
  
  /**
  * This method will apply the sharpening filter
  * to the image.
  */
  
  void applySharpening();
  
  /**
  * This method will apply the grey scale filter
  * to the image.
  */
  
  void applyGreyScale();
  
  /**
  * This method will apply the sepia tone filter
  * to the image.
  */
  
  void applySepiaTone();
  
  /**
  * This method will apply the reduce color density of
  * the image.
  * @param colorPerChannel   how many colors per channel.
  */
  
  void applyReduceColorDensity(int colorPerChannel);
  
  /**
  * This method will apply the Mosaic filter to the 
  * image with number of random seeds equals to seeds.
  * @param seeds   the number of random seeds for the
  *                mosaic operation.
  */
  
  void applyMosaic(int seeds);
  
  /**
  * This method will apply the pixelation filter to the
  * image with number of blocks across the width equals to
  * blockNum.
  * @param blockNum  the number of super pixels block across
  *                  the width.
  */
  
  void applyPixelation(int blockNum);
  
  /**
  * This method will generate the cross stich pattern and
  * output it to a text file.
  * @param blockNum  the number of super pixels block across
  *                  the width for the pixelation.
  * @param fileName  the output txt file name.
  * @throws IOException  Will throw an exception if the file name
  *                      is invalid.
  */
  
  void generatePattern(int blockNum, String fileName) throws IOException;

  /**
  * This method will generate the output image.
  * @param fileName   the image's filename.
  * @throws IOException  will throw an exception if the file 
  *                      name is invalid.
  */
  
  void generateOutPutImage(String fileName) throws IOException;
  
  /**
  * This method will load the image from the input file.
  * @param fileName  the file name of the input image. 
  * @throws IOException  will throw an IOException if the input
  *                      image file is not in the current directory.
  */
  
  void loadImage(String fileName) throws IOException;
  
  /**
  * Set ImageModel's image to the input parameter.
  * @param image   the new image value.
  */
  
  void setImage(int[][][] image);
  
  /**
  * This method will return the image in 3d integer array.
  * @return   a 3d integer array that represent the image.
  */
  
  int[][][] getImage();
  
}
