package mvc;


import java.io.IOException;

/**
* The IView interface that represents the view part
* of this mvc program. In this IView, Java Swing is implemented.
* @author luzixiao.
*/
public interface IView {
  
  /**
  * This method sets all the components in the JFrame
  * to its corresponding ActionListener.
  * @param feature     feature interface that uses call back
  *                    functions to do image processing operations.
  * @throws IOException      Will throw IOException when trying to load
  *                          file or save file to an unknown path.
  */
  void setOperations(Feature feature) throws IOException;
  
  /**
  * This method will display a pop up window that ask
  * user to input an integer.
  * @param hint    the hint displayed on the pop up window.
  * @return        the number that user inputed.
  */
  
  int popUpWindow(String hint);
  
  /**
  * This method will open a dialog that display the 
  * input string message.
  * @param mesage     the message you want to display
  *                   on the dialog.
  */
  
  public void showMessageDialog(String mesage);
  
}
