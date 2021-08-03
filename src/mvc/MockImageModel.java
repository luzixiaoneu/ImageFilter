package mvc;


/**
* MockImageModel class that mocks the image model
* in MVC and use for testing.
* @author luzixiao.
*/
public class MockImageModel {

  /**
  * private fields used for this class.
  * image:   3d integer array represents the image.
  */
  
  private int[][][] image;

  /**
  * This method set the model's image.
  * @param image    the image you want to set.
  */
  
  public void setImage(int[][][] image) {
    this.image = image;
    
  }

  /**
  * This method return the current image
  * 3d integer array.
  * @return
  */
  
  public int[][][] getImage() {
    return this.image;
  }




}
