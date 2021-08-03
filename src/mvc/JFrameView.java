package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import images.DmcColor;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
* The JFrameView class implementation that extends the JFrame
* Class. All the components and action listeners in the UI
* will be set in this class.
* @author luzixiao.
*/

public class JFrameView extends JFrame implements IView {
  
  /**
  * All the private fields that will be used in the JFrameView.
  * serialVersionUID:  a long number for the version id.
  * label:   an JLabel that holds the image to be displayed.
  * patternPage:   a JDialog that represents the cross stitch pattern page.
  *                All the current super pixels will be displayed in this dialog
  *                for additaional color replacement or removing.
  * legendPage:    a JDialog that represents the current dmc color legend in used
  *                in the current pattern.
  * colorPlatterPage:    a JDialog that represents all 489 dmc colors that the users
  *                      can use to substitute with the current dmc colors.
  * runMenu:       a JMenu that holds all the MenuItems for different image processing
  *                operations.
  * fileMenu:      a JMenu that holds all the Menu for different file operations.
  */
  
  private static final long serialVersionUID = -4916322697472335477L;
  private List<DmcColor> dmcLegend;
  private JLabel label;
  private JDialog patternPage;
  private JDialog legendPage;
  private JDialog colorPlatterPage;
  private JMenu runMenu;
  private JMenu fileMenu;

  
  /**
  * Constructor for JFrameView.The constructor will
  * initialize the private fields. 
  * @param caption     the caption for this frame.
  */
  
  public JFrameView(String caption) {
    super(caption);
    patternPage = new JDialog();
    legendPage = new JDialog();
    colorPlatterPage = new JDialog();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JMenuBar menuBar = new JMenuBar();
    this.runMenu = new JMenu("Run");
    this.fileMenu = new JMenu("File");
    JPanel mainPanel = new JPanel();
    this.label = new JLabel(new ImageIcon());
    JPanel imagePanel = new JPanel();
    imagePanel.add(label, BorderLayout.NORTH);
    JScrollPane scrollPane = new JScrollPane(imagePanel);     
    menuBar.add(this.fileMenu);
    menuBar.add(this.runMenu);    
    mainPanel.add(menuBar);
    this.add(mainPanel, BorderLayout.NORTH);
    this.add(scrollPane, BorderLayout.CENTER);        
    this.setSize(1200, 1000);
    this.setResizable(true);
    this.setVisible(true);
  }
  
  /**
  * A private helper method that set the action listener for
  * load JMenuItem.
  * @param feature    feature you trying to add to the listener.
  * @throws IOException    Will throw an IOException if the file
  *                        path is invalid.
  */
  
  private void setLoadMenuItem(Feature feature) throws IOException {
    JMenuItem load = new JMenuItem("Load Image");
    load.addActionListener(loadImageButton(feature));
    this.fileMenu.add(load);
    
  }
  
  /**
  * A private helper method that set the action listener for
  * save JMenuItem.
  * @param feature    feature you trying to add to the listener.
  * @throws IOException    Will throw an IOException if the file
  *                        path is invalid.
  */
  
  private void setSaveMenuItem(Feature feature) throws IOException {
    JMenuItem save = new JMenuItem("Save Image");
    save.addActionListener(saveImageButton(feature));
    this.fileMenu.add(save);
  }
  
  /**
  * A private helper method that set the action listener for
  * create script JMenuItem.
  * @param feature    feature you trying to add to the listener..
  * @throws IOException    Will throw an IOException if the file
  *                        path is invalid.
  */
  
  private void setCreateScriptMenuItem(Feature feature) {
    JMenuItem script = new JMenuItem("Create Script");
    script.addActionListener(createScriptAction(feature));
    this.fileMenu.add(script);
  }

  /**
  * A private helper method that set the action listener for
  * differen image processing buttons except cross stitch pattern. 
  * Doing this to avoid code dupilcate.
  * @param feature    feature you trying to add.
  * @param functions  function arguments you are trying to add to the 
  *                   listener in the feature interface.
  * @param menuTitle  title for the menuItem
  * @throws IOException    Will throw an IOException if the file
  *                        path is invalid.
  */
  
  private void imageProcessingOperations(Feature feature, Consumer<Void> functions, 
      String menuTitle) {
    JMenuItem meunItem = new JMenuItem(menuTitle);
    meunItem.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        if (feature.getImage() == null) {
          showMessageDialog("Please Load Image First!");
          return;
        }
        functions.accept(null);
        label.setIcon(new ImageIcon(feature.getImage()));
        showMessageDialog("Complete!"); 
        
      } 
    } );
    this.runMenu.add(meunItem);
  } 

  /**
  * Private helper method that sets the action listener
  * for cross sititch pattern MenuItem.
  * @param feature    the feature you want to add to the
  *                   listener.
  */
  
  private void setCrossStitchMenuItem(Feature feature) {
    JMenuItem pattern = new JMenuItem("Cross Stitch Pattern");
    pattern.addActionListener(generateCrossStitchPattern(feature));
    runMenu.add(pattern);
  }
  
  
  @Override
  public void setOperations(Feature feature) throws IOException {
    Consumer<Void> blur = a -> feature.blur();
    Consumer<Void> sharpening = a -> feature.sharpening();
    Consumer<Void> greyScale = a -> feature.greyScale();
    Consumer<Void> sepiaTone = a -> feature.sepiaTone();
    Consumer<Void> reduceColorDensity = a -> feature.reduceColorDensity(
        this.popUpWindow("Please enter the number of color per channel "
            + "(Enter a number greater than 0!)"));
    Consumer<Void> mosaic = a -> feature.mosaic(
        this.popUpWindow("Please Enter The Number of Seeds For Mosaic "
            + "(Enter a number greater than 0!)"));
    Consumer<Void> pixelation = a -> feature.pixelation(
        this.popUpWindow("Please Enter The Chunk Number For Pixelation "
            + "(Enter a number greater than 0!)"));   
    this.imageProcessingOperations(feature, blur, "Blur Image");
    this.imageProcessingOperations(feature, sharpening, "Sharpening Image");
    this.imageProcessingOperations(feature, greyScale, "Grey Scale Image");
    this.imageProcessingOperations(feature, sepiaTone, "Sepia Tone Image");
    this.imageProcessingOperations(feature, reduceColorDensity, "Reduce Color Density Image");
    this.imageProcessingOperations(feature, mosaic, "Mosaic Image");
    this.imageProcessingOperations(feature, pixelation, "Pixelation Image");   
    setLoadMenuItem(feature);  
    setSaveMenuItem(feature);
    setCrossStitchMenuItem(feature); 
    setCreateScriptMenuItem(feature);
  }
  
  /**
  * A private helper method that create the action listener for
  * generate the cross stitch pattern.
  * @param feature     the feature you want to add to the listener.
  * @return            a ActionListener that opens two pages, one represents
  *                    the current grid of all the super pixels for corss stitch
  *                    pattern and other page for DMC lgenend that represents all
  *                    the current in use DMC color in the current pattern.
  */
  
  private ActionListener generateCrossStitchPattern(Feature feature) {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          if (feature.getImage() == null) {
            showMessageDialog("Please Load Image First!");   
            return;
          }              
          patternPage.setTitle("Current Pattern");
          int chunkNum = popUpWindow("Please Enter The Chunk Number For Pattern");
          patternPage.add(feature.createGridView(chunkNum));
          dmcLegend = feature.getLegendInUse();
          JPanel buttonPanels = new JPanel();
          JPanel buttonHolder = new JPanel();
          buttonPanels.setLayout(new BoxLayout(buttonPanels, BoxLayout.Y_AXIS));
          buttonHolder.setLayout(new GridLayout(50, 100));
          for (int i = 0; i < dmcLegend.size(); i++) {         
            String temp = "DMC-" + dmcLegend.get(i).getId() 
                + " Symbol-" + dmcLegend.get(i).getSymbol();
              JButton current = new JButton();            
              current.setText(temp);
              current.setBackground(new Color(dmcLegend.get(i).getRed(), 
                  dmcLegend.get(i).getGreen(), dmcLegend.get(i).getBlue()));
              current.setPreferredSize(new Dimension(50, 50));
              buttonHolder.add(current);
              current.addActionListener(openAllDmcColorPlatterAction(feature, current));           
          }
          buttonPanels.add(buttonHolder);
          legendPage.setTitle("Current DMC Legend in the Use");
          legendPage.add(new JScrollPane(buttonPanels));
          JPanel save = new JPanel();
          JButton saveButton = new JButton("Display Full Image");
          JButton generateTxtButton = new JButton("Generate Txt File");
          generateTxtButton.addActionListener(generateTxtFileForCrossStitchPatternAction(feature));
          saveButton.addActionListener(savePatternButtonAction(feature));
          save.add(saveButton);
          save.add(generateTxtButton);
          legendPage.add(save, BorderLayout.SOUTH);
          showMessageDialog("Complete!");
          patternPage.setSize(600, 600);       
          patternPage.setVisible(true);
          patternPage.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);                
          legendPage.setSize(600, 600);       
          legendPage.setVisible(true);
          legendPage.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
        } catch (IOException exception) {
          showMessageDialog("Invalid Path");
          exception.printStackTrace();
        }
        
      }
      
    };
  }
  
  
  
  
  
  
  
  /**
  * Returns an ActionListener that geneerate the txt file when user
  * in ui finished with choosing color for stitching and want to generate the 
  * txt file contains the current pattern.
  * @param feature       the feature you want to add.
  * @return              an ActionListener that generate the txt file for the
  *                      cross stitch pattern.
  */
  
  private ActionListener generateTxtFileForCrossStitchPatternAction(Feature feature) {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
          String path = fileChooser.getSelectedFile().getAbsolutePath();     
          try {
            feature.setDmcLegend(dmcLegend);
            feature.drawPatternForUI(path);
            showMessageDialog("File generated!");
          } catch (IOException e1) {
            showMessageDialog("Invalid path!");
            e1.printStackTrace();
          }
        }
      }
    } ;
  }
  
  
  
  
  
  /**
  * Returns an ActionListener that opens new page contains all
  * 489 dmc colors for users to choose and replace with their current pattern.
  * @param feature      the feature you want to add.
  * @param oldColor     the old color to be replaced.
  * @return             an ActionListener that opens a page contains all 489
  *                     dmc colors.
  */
  
  private ActionListener openAllDmcColorPlatterAction(Feature feature, JButton oldColor) {
    ActionListener result = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {   
        int oldIndex = feature.findIndexFromLegendInUse(oldColor);        
        if (showRemoveOrReplaceDialog() == JOptionPane.YES_OPTION) {          
          DmcColor discardColor = dmcLegend.get(oldIndex);
          discardColor.setSymbol("*");
          dmcLegend.set(oldIndex, discardColor);
          oldColor.setText("Removed! Symbol*");
          feature.displayDiscardedPattern(oldColor.getBackground());         
          return;
        }
        JPanel allDmcColor = new JPanel(); 
        JPanel allDmcColorHolder = new JPanel();
        allDmcColor.setLayout(new BoxLayout(allDmcColor, BoxLayout.Y_AXIS));
        allDmcColorHolder.setLayout(new GridLayout(50, 100));
        int size = feature.getDmcChart().size();
        for (int i = 0; i < size; i++) {
          JButton tmp = new JButton();
          String name = "DMC-" + feature.getDmcChart().get(i).getId() 
              + feature.getDmcChart().get(i).getName();
          tmp.setText(name);
          int red = feature.getDmcChart().get(i).getRed();
          int green = feature.getDmcChart().get(i).getGreen();
          int blue = feature.getDmcChart().get(i).getBlue();
          String symbol = feature.getDmcChart().get(i).getSymbol();
          String id = feature.getDmcChart().get(i).getId();
          tmp.setBackground(new Color(red, green, blue));         
          tmp.addActionListener(changeColorAction(feature, oldColor, oldIndex, symbol, id));
          allDmcColorHolder.add(tmp);
          
        }        
        allDmcColor.add(allDmcColorHolder);
        colorPlatterPage = new JDialog();     
        colorPlatterPage.add(new JScrollPane(allDmcColor));
        colorPlatterPage.setVisible(true);
        colorPlatterPage.setSize(400, 400);
        colorPlatterPage.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      }     
    };
    return result;
    
  }
  
  /**
  * Retunrs an ActionListener that act to the change color in the cross stitch
  * pattern when the user in UI clicked on one of the current in use DMC Color in the
  * legend and replace that color with another selected color in the 489 dmc colors.
  * @param feature     the feature you want to add to this action.
  * @param oldColor    the old Colro from current in use DMC 
  *                    legend the user is trying to replace.   
  * @param oldIndex    the old Color from current in use DMC 
  *                    legend's index the user is trying to replace.
  * @param symbol      the oldColor's symbol.
  * @param id          the oldColor's Dmc id.
  * @return            an action listener that changes the color.  
  */
  
  private ActionListener changeColorAction(Feature feature, JButton oldColor, 
      int oldIndex, String symbol, String id) {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JButton newColor = (JButton) e.getSource();
        feature.applyChangeToGrid(oldColor.getBackground(), newColor.getBackground()); 
        oldColor.setText(newColor.getText());
        oldColor.setBackground(newColor.getBackground());
        DmcColor newDmc = dmcLegend.get(oldIndex);
        newDmc.setRed(newColor.getBackground().getRed());
        newDmc.setGreen(newColor.getBackground().getGreen());
        newDmc.setBlue(newColor.getBackground().getBlue());
        newDmc.setSymbol(symbol);
        newDmc.setId(id);
        dmcLegend.set(oldIndex, newDmc);
        feature.setDmcLegend(dmcLegend);
        colorPlatterPage.setVisible(false);       
      }     
    };
  }
  
  
  
  
  /**
  * Returns an ActionListener that generate the current
  * cross stitch pattern into an image to be displayed in the ui.
  * @param feature     the feature you want to add.
  * @return            an ActionListener that save the current pattern
  *                    to an image to be displayed.
  */
  
  private ActionListener savePatternButtonAction(Feature feature) {
    ActionListener result = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      
          feature.setImageAfterStitchPattern();
          ImageIcon image = new ImageIcon(feature.getImage());
          label.setIcon(image);
          colorPlatterPage.setVisible(false);
          patternPage.setVisible(false);
          legendPage.setVisible(false);
          label.setIcon(image);
             
      }     
    };
    return result;
  }
  
  /**
  * This Action Listener will save the current image 
  * to an image file the user specified in the JFileChooser.
  * @param feature     the feature you want to add.
  * @return        an ActionListener that generate the output
  *                image file.
  */
  
  private ActionListener saveImageButton(Feature feature) {
    ActionListener result = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (feature.getImage() == null) {
          showMessageDialog("Please Load Image First!");
          return;
        }
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showSaveDialog(fileChooser);
        if (response == JFileChooser.APPROVE_OPTION) {
          String path = fileChooser.getSelectedFile().getAbsolutePath();
          try {
            feature.saveImage(path);
            showMessageDialog("Save Complete!");
          } catch (IOException exception) {
            exception.printStackTrace();
          }
        }       
      }
    };
    return result;
  }
  
  /**
  * This ActionListener will load the image from the path that the
  * user selected in the FileChooser to the UI.
  * @param feature     the feature you want to add.
  * @return     an ActionListener that load the image from file 
  *             and display it in the UI.
  */
  private ActionListener loadImageButton(Feature feature) {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files(jpg, png)",
            "png", "jpg");
        fileChooser.setFileFilter(filter);
        int response = fileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
          String path = fileChooser.getSelectedFile().getAbsolutePath();
          try {
            feature.loadImage(path);
            ImageIcon image = new ImageIcon(feature.getImage());
            label.setIcon(image);
            showMessageDialog("Load Complete!");

          } catch (IOException exception) {
            exception.printStackTrace();
          }
        }        
      }      
    };
  }
  
  /**
  * This ActionListener will open a JTextArea and let the user 
  * create an script and execute the script.
  * @param feature      the feature you want to add.
  * @return     an ActionListener that creates the JTextArea
  *             as the script.
  */
  private ActionListener createScriptAction(Feature feature) {
    return new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        JTextArea textArea = new JTextArea(50, 50);
        JScrollPane scroller = new JScrollPane(textArea);
        JDialog scriptWindow = new JDialog();
        scriptWindow.setTitle("Script");
        scriptWindow.add(scroller, BorderLayout.NORTH);
        JButton execute = new JButton("Execute");
        execute.addActionListener(executeScriptAction(feature,textArea));
        JPanel buttonPanel = new JPanel();   
        buttonPanel.add(execute);
        scriptWindow.add(buttonPanel, BorderLayout.CENTER);   
        scriptWindow.setSize(200, 200);       
        scriptWindow.setVisible(true);
        scriptWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);    
      }
      
    };
    
  }
  
  /**
  * This ActionListener will execute the script the user created.
  * @param feature     the feature you want to add.
  * @param textArea    the textArea that user input their script.
  * @return            an ActionListener that execute the script.
  */
  private ActionListener executeScriptAction(Feature feature, JTextArea textArea) {
    return new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          feature.executeScript(textArea); 
          showMessageDialog("Script Execute Complete!");
        } catch (IOException e1) {  
          showMessageDialog("Invalid path!");
        } catch (IllegalArgumentException e2) {
          showMessageDialog("Wrong Syntax");
        }       
      }

    };
  }

 
  @Override
  public int popUpWindow(String hint) {
    return Integer.parseInt(JOptionPane.showInputDialog(hint));    
  }
 
  @Override
  public void showMessageDialog(String mesage) {
    JOptionPane.showMessageDialog(this, mesage);
  }

  /**
  * a private helper method that ask user whether they
  * want to replace a new color or remove that color from
  * the DMC legend.
  * @return     a JOptionPane that returns the user's selection.
  */
  
  private int showRemoveOrReplaceDialog() {
    String[] options = {"Remove", "Replace"}; 
    int result = JOptionPane.showOptionDialog(legendPage, "Remove This DMC or Replace?", 
        "Remove Or Replace", 
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    return result;
  }
  
  

  



  
  
  
  

}
