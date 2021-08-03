package images;

/**
* a class represents all the DMC colors that will
* be used in the cross-stitch pattern.
* @author luzixiao.
*/
public class DmcColor {

  /**
  * All the private fields in the class.
  * red:   the red value of this DMC color.
  * green: the green value of this DMC color.
  * blue:  the blue value of this DMC color.
  * id:    the DMC ID number.
  * name:  the DMC's name.
  * symbol:  the Unicode symbol that represent this dmc color.
  * index:  the index of this dmc color in the dmcChart.
  */
  
  private int red;
  private int green;
  private int blue;
  private String id;
  private String name;
  private String symbol;
  private int index;
  
  /**
  * Constructor for the DmcColor object.
  * @param id   the DMC's ID number.
  * @param name  the name of this DMC color.
  * @param red   the red value of this DMC color.
  * @param green  the green value of this DMC color.
  * @param blue   the blue value of this DMC color.
  * @param symbol    the symbol of this DMC color.
  * @param index    the index of this DMC color in the dmc chart.
  */
  
  public DmcColor(String id, String name, int red, int green, int blue, String symbol, int index) {
    if (name == null || id == null || symbol == null) {
      throw new IllegalArgumentException();
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.id = id;
    this.name = name;
    this.symbol = symbol;
    this.index = index;
  }
  
  /**
  * return the id of this dmc color.
  * @return    the id of this dmc color.
  */
  public String getId() {
    return this.id;
  }
  
  /**
  * return the red component of this dmc color.
  * @return    the red component of this dmc color.
  */
  public int getRed() {
    return this.red;
  }
  
  /**
  * return the blue component of this dmc color.
  * @return    the blue component of this dmc color.
  */
  
  public int getBlue() {
    return this.blue;
  }
  
  /**
  * return the green component of this dmc color.
  * @return    the green component of this dmc color.
  */
  
  public int getGreen() {
    return this.green;
  }
  
  /**
  * return the name of this dmc color.
  * @return    the name of this dmc color.
  */
  
  public String getName() {
    return this.name;
  }
  
  /**
  * return the symbol of this dmc color.
  * @return    the symbol of this dmc color.
  */
  public String getSymbol() {
    return this.symbol;
  }
  
  /**
  * return the index of this dmc color in the dmcChart.
  * @return   the index of this dmc color in the dmcChart.
  */
  public int getIndex() {
    return this.index;
  }
  
  /**
  * set the green value for this dmc color.
  * @param green    the green value you want to set.
  */
  public void setGreen(int green) {
    this.green = green;
  }
  
  /**
  * set the red value for this dmc color.
  * @param red    the red value for this dmc color.
  */
  
  public void setRed(int red) {
    this.red = red;
  }
  
  /**
  * set the blue value for this dmc color.
  * @param blue    the blue value for this dmc color.
  */
  
  public void setBlue(int blue) {
    this.blue = blue;
  }
  
  /**
  * set the symbol for this dmc color.
  * @param symbol    the symbol for this dmc color.
  */
  
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }
  
  /**
  * set the ID for this dmc color.
  * @param id    the id you want to set.
  */
  
  public void setId(String id) {
    this.id = id;
  }
  
 
  
  
  
}
