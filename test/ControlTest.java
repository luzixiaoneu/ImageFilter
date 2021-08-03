import static org.junit.Assert.assertEquals;
import java.awt.Color;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import mvc.Feature;
import mvc.MockController;
import mvc.MockImageModel;
import mvc.MockView;


/**
* JUnit test for controller. 
* @author luzixiao.
*/
public class ControlTest {

  /**
  * All the private data fields that will
  * be used in this JUnit test.
  */
  
  private MockView mockView;
  private Feature mockController;
  

  /**
  * Set up all the private fields that will be used in the 
  * JUnit test.
  */
  
  @Before
  public void setUp() {
    MockImageModel mockModel = new MockImageModel();
    this.mockView = new MockView();
    this.mockController = new MockController(mockModel);
  }
  
  /**
  * Test to load a test.jpg file to the view.
  * Expect:   load test.jpg.
  * @throws  IOException     will throws IOException if file path is not legal.
  */
  @Test
  public void testLoadImage() throws IOException {
    mockView.loadImage(mockController, "test.jpg");
    assertEquals("load test.jpg ", mockController.toString());
  }
  
  /**
  * Test to load a null filepath to the view.
  * @throws IOException   will throws IOException if file path is not legal.
  */
  
  @Test (expected = IOException.class)
  public void testLoadIllegalFilePath() throws IOException {
    mockView.loadImage(mockController, null);
  }
  
  /**
  * Test to save the image from the view as output.jpg.
  * Expect:    save output.jpg
  * @throws IOException   will throws IOException if file path is not legal.
  */
  @Test
  public void testSaveImage() throws IOException {
    mockView.loadImage(mockController, "input.jpg");
    mockView.saveImage(mockController, "output.jpg");
    assertEquals("load input.jpg save output.jpg ", mockController.toString());
  }
  
  /**
  * Test to save a null filepath from the view.
  * @throws IOException  will throws IOException if file path is not legal.
  */
  
  @Test (expected = IOException.class)
  public void testSaveIllegalFilePath() throws IOException {
    mockView.saveImage(mockController, null);
  }
  
  /**
  * Test to save to filepath from the view before load image.
  * Expect: IllegalStateException. 
  * @throws IOException    will throws IOException if file path is not legal.
  * 
  */
  
  @Test (expected = IllegalStateException.class)
  public void testSaveBeforeLoad() throws IOException {
    mockView.saveImage(mockController, "output.jpg");
  }
  
  /**
  * Test load input then blur it.
  * Expect: load input blur
  * @throws IOException  will throws IOException if file path is not legal.
  */
  
  @Test
  public void testBlur() throws IOException {
    mockView.loadImage(mockController, "input");
    mockView.blurImage(mockController);
    assertEquals("load input blur ", mockController.toString());
  }
  
  /**
  * Test blur without load any image.
  * Expect: IllegalStateException.
  */
  
  @Test (expected = IllegalStateException.class)
  public void testBlurWithoutLoadImage() {
    mockView.blurImage(mockController);
  }
  
  /**
  * Test load input then sharpening it.
  * Expect: load input sharpening
  * @throws IOException  will throws IOException if file path is not legal.
  */
  
  @Test
  public void testSharpening() throws IOException {
    mockView.loadImage(mockController, "input");
    mockView.sharpeningImage(mockController);
    assertEquals("load input sharpening ", mockController.toString());
  }
  
  /**
  * Test sharpening without load any image.
  * Expect: IllegalStateException.
  */
  
  @Test (expected = IllegalStateException.class)
  public void testSharpeningWithoutLoadImage() {
    mockView.sharpeningImage(mockController);
  }
  
  /**
  * Test load input then grey scale it.
  * Expect: load input greyscale.
   * @throws IOException  will throws IOException if file path is not legal.
  */
  
  @Test
  public void testGreyScale() throws IOException {
    mockView.loadImage(mockController, "input");
    mockView.greyScale(mockController);
    assertEquals("load input greyscale ", mockController.toString());
  }
  
  /**
  * Test grey scale without load any image.
  * Expect: IllegalStateException.
  */
  
  @Test (expected = IllegalStateException.class)
  public void testGreyScaleWithoutLoadImage() {
    mockView.greyScale(mockController);
  }
  
  /**
  * Test load input then sepia tone it.
  * Expect: load input blur
   * @throws IOException  will throws IOException if file path is not legal.
  */
  
  @Test
  public void testSepiaTone() throws IOException {
    mockView.loadImage(mockController, "input");
    mockView.sepiaTone(mockController);
    assertEquals("load input sepiatone ", mockController.toString());
  }
  
  /**
  * Test sepia tone without load any image.
  * Expect: IllegalStateException.
  */
  
  @Test (expected = IllegalStateException.class)
  public void testSepiaToneWithoutLoadImage() {
    mockView.sepiaTone(mockController);
  }
  
  /**
  * Test load input then pixelation it with 100 chunks.
  * Expect: load input pixelation 100
  * @throws IOException  will throws IOException if file path is not legal.
  */
  
  @Test
  public void testPixelation() throws IOException {
    mockView.loadImage(mockController, "input");
    mockView.pixelation(mockController, 100);
    assertEquals("load input pixelation 100 ", mockController.toString());
  }
  
  /**
  * Test pixelation without load any image.
  * Expect: IllegalStateException.
  */
  
  @Test (expected = IllegalStateException.class)
  public void testPixelationWithoutLoadImage() {
    mockView.pixelation(mockController, 100);
  }
  
  /**
  * Test reduce pixelation with negative chunk numbers.
  * Expect: IllegalArgumentException.
  */
  
  @Test (expected = IllegalArgumentException.class)
  public void testPixelationWithoutIllegalArgument() {
    mockView.pixelation(mockController, -1);
  }
  
  /**
  * Test load input then reduce color density it with 5 color per channel.
  * Expect: load input pixelation 100
   * @throws IOException  will throws IOException if file path is not legal.
  */
  
  @Test
  public void testReduceColorDensity() throws IOException {
    mockView.loadImage(mockController, "input");
    mockView.reduceColorDensity(mockController, 5);
    assertEquals("load input reduce 5 ", mockController.toString());
  }
  
  /**
  * Test reduce color density without load any image.
  * Expect: IllegalStateException.
  */
  
  @Test (expected = IllegalStateException.class)
  public void testReduceColorDensityWithoutLoadImage() {
    mockView.reduceColorDensity(mockController, 5);
  }
  
  /**
  * Test reduce color density with negative color per channel.
  * Expect: IllegalArgumentException.
  */
  
  @Test (expected = IllegalArgumentException.class)
  public void testReduceColorDensityWithoutIllegalArgument() {
    mockView.reduceColorDensity(mockController, -1);
  }
  
  
  /**
  * Test load input then mosaic it with 500 seedns.
  * Expect: load input mosaic 500.
   * @throws IOException  will throws IOException if file path is not legal.
  */
  
  @Test
  public void testMosaic() throws IOException {
    mockView.loadImage(mockController, "input");
    mockView.mosaic(mockController, 500);
    assertEquals("load input mosaic 500 ", mockController.toString());
  }
  
  /**
  * Test mosaic without load any image.
  * Expect: IllegalStateException.
  */
  
  @Test (expected = IllegalStateException.class)
  public void testMosaicWithoutLoadImage() {
    mockView.mosaic(mockController, 500);
  }
  
  /**
  * Test mosaic with negative color per channel.
  * Expect: IllegalArgumentException.
  */
  
  @Test (expected = IllegalArgumentException.class)
  public void testMosiacWithoutIllegalArgument() {
    mockView.mosaic(mockController, -1);
  }
  
  /**
  * Test execute the script that is created in the 
  * interactive mode.
  * Expect:  run script. 
  * @throws IOException    will throw an IOException if
  *                        load or save with an invalid 
  *                        path.
  */
  
  @Test
  public void testExecuteScript() throws IOException {
    mockView.executeScript(mockController, "load input.jpg");
    assertEquals("run script ", mockController.toString());
  }
  
  /**
  * Test execute the script that has no content.
  * Expect:  IOException.
  * @throws IOException    will throw an IOException if
  *                        load or save with an invalid 
  *                        path.
  */
  
  @Test (expected = IOException.class)
  public void testExecuteScriptWithEmptyContent() throws IOException {
    mockView.executeScript(mockController, null);
    assertEquals("run script ", mockController.toString());
  }
  
  /**
  * Test to create cross stitch pattern in the UI.Load image firs.
  * Expect:  load input grid view generated .
  * @throws IOException     will throw IOException.
  * 
  */
  
  @Test
  public void testCreateCrossStitchPatternInUi() throws IOException {
    mockView.loadImage(mockController, "input");
    mockView.crossStitch(mockController, 100);
    assertEquals("load input grid view generated ", mockController.toString());
  }
  
  /**
  * Test to create cross stitch pattern in the UI without load image.
  * Expect:  IllegalStateException.
  * @throws IOException     will throw IOException.
  * 
  */
  
  @Test (expected = IllegalStateException.class)
  public void testCreateCrossStitchPatternInUiWithoutLoad() throws IOException {
    mockView.crossStitch(mockController, 100);

  }
  
  /**
  * Test to create cross stitch pattern in the UI with negative chunk bumber.
  * Expect:  IllegalArgumentException.
  * @throws IOException     will throw IOException.
  * 
  */
  
  @Test (expected = IllegalArgumentException.class)
  public void testCreateCrossStitchPatternInUiWithNegativeChunk() 
      throws IOException {
    mockView.crossStitch(mockController, -1);
  }
  
  /**
  * Test to replace the current DmcLegend with another Dmc
  * color.
  * Expect: color replaced updating current dmc lengend.
  */
  
  @Test
  public void testChangeColorInCrossStitch() {
    mockView.changeStitchPattern(mockController, Color.red, Color.gray);
    assertEquals("color replaced updating current dmc lengend ", mockController.toString());
  }
  
  /**
  * Test to replace the current DmcLegend with another Dmc
  * color. change color 3 times.
  * Expect: color replaced updating current dmc lengend 
  *         color replaced updating current dmc lengend 
  *         color replaced updating current dmc lengend.
  */
  
  @Test
  public void testChangeColorInCrossStitchThreeTimes() {
    mockView.changeStitchPattern(mockController, Color.red, Color.gray);
    mockView.changeStitchPattern(mockController, Color.gray, Color.blue);
    mockView.changeStitchPattern(mockController, Color.white, Color.black);
    assertEquals("color replaced updating current dmc lengend "
        + "color replaced updating current dmc lengend "
        + "color replaced updating current dmc lengend ", mockController.toString());
  }
  
  /**
  * Test to display the image after cross stitch.
  * Expect: displaying image after cross stitch for UI .
  */
  
  @Test
  public void testDisplayImageAfterCrossStitch() {
    mockView.displayImageAfterStitchPattern(mockController);
    assertEquals("displaying image after cross stitch for UI ", mockController.toString());
  }
  
  /**
  * Test to drawy the pattern in txt after cross stitch.
  * Expect:  generate txt file in UI mode .
   * @throws IOException will throw IOException.
  */
  
  @Test
  public void testGenerateTxtAfterCrossStitch() throws IOException {
    mockView.generatePatternTxtFileAfterStitchPattern(mockController, "result.txt");
    assertEquals("generate txt file in UI mode ", mockController.toString());
  }
  
  

}
