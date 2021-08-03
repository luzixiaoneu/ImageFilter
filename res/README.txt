1. About/Overview
This project implements a fully functional image processing java program. Currently it supports the following
features:Load Image, Save Image, Blur, Sharpening, GreyScale, SepiaTone, Reduce Color Density, Mosaic, Pixelation and CrossStitch Pattern.
This program has 2 modes, one is to open the UI (Interactive Mode) and the other is to execute the image processing
using a pre existed script in txt file.
2. List of Features:
Interactive Mode (UI):
Load and Display the image.
Apply Blur filter
Apply Sharpening filter
Apply Greyscale filter
Apply Sepia Tone filter
Apply Reduce Color Desnsity
Apply Mosaic filter with any number of seeds
Apply Pixelation with any number of chunks
Apply Cross Stitch Pattern and allow user to select any color in the current pattern to replace with
another DMC color. *
Save the processed Image as an Image file.
Generate the txt file contains the pattern of the cross stitch. 
Create a script interactively and execute the script. *

Script Mode：
Execute the above Features using pre made script. (Except for *)

3. How to Run
Download the ImageProcesser.jar file to your local disk. In addition, download the dmc.csv file to the SAME directory.
Make sure you are using UTF-8 encoding.
In windows use the following command to run:
java -jar ImageProcesser.jar -interactive  (Interactive Mode)
java -jar ImageProcesser.jar -script path-to-script-file  (Script Mode)
Note: If you want to use the script mode, make sure the file you want to process are in the same directory as well.


4. How to Use the Program
In interactive mode, this program will provide an user interface. On top of the UI, there are two menus, File and Run. Load/Save/Create Script
are listed under File menu and all image processing operations are under Run menu.
In script mode, the following syntax are need inorder for the script mode to run properly:
load image       ->            load  image-file-name                                ex: load input.jpg
save image       ->            save  image-file-name
apply blur       ->            blur
apply sharpening ->            sharpening
apply greyscale  ->            greyscale
apply sepiatone  ->            sepiatone
apply mosaic     ->            mosaic number-of-seeds-in-integer                    ex: mosaic 100
apply pixelation ->            pixelation number-of-chunks-in-integer               ex: pixelation 100
apply cross stitch pattern  -> pattern output-filename                              ex: pattern output.txt
This syntax is also apply to the interactive mode's create script.


5. Example run: 
in the folder /res, there is a screenshot uiExample that shows the base look of the UI.
in the folder /res, there is a screenshot createScript that shows the interactive script page.
in the folder /res, there is a screenshot corssStitchPattern that shows the corss stitch pattern.
In the example run, I will cross-stitch the image 48.jpg and replace all the black patterns in the wheel
area to red and display that to the UI. The screenshots is example-part1.png, example-part2.png and example-part2.png.

6. Desing/Model changes
The design changes quite a lot from my original design. In order to fully seperate the view and the model, I decided to add another 
interface Feature that the controlller will implement. Then I use call back functions in the view to call different method of feature
when adding different action listener to the UI. By doing so, my Model, View and Controller can be seperate and the view contains as
few logic as possible.

7. Assumptions:
No Assumptions were made particulaly during the implementation and development.

8. Limitations:
All the requirements for this projects are fufilled. For extra credit parts, part 1 is done. 

9. Citation.
The provided photo 48.jpg was taken by me, and I have authorized the use of this image for NEU CS 5010.
The starbucks logo pictures is used only for educational purposes. 



