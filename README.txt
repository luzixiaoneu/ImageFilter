1. About/Overview
This project is built on project 3 with 3 more additional functions:
1. Mosiaic filter
2. Pixelation filter
3. Cross Stitch pattern

2. List of Features
Currently this program supports the following features:
1. Blur image
2. sharpening image
3. Apply grey scale to the image
4. Apply sepia tone to the image
5. Reduce the color density to a specific color number per channel.
6. Applying image mosaic
7. Applying image pixelation
8. Generate corss-stitch pattern

3. How to run
Download the ImageProcesser.jar file to your local disk. Also, download the dmc.csv file to the same directory as the ImageProcesser.jar file.
Create a txt file name command.txt and write the operations you want inside that file. In the end, put all the pictures you want to process
to this same directory as well.
**********Important**********
Note: All the input file has to be in the format of UTF-16 encoded.
*****************************
After these steps, start the program by running the jar file or using command java -jar ImageProcesser.jar.

4. How to use the Program
**********Important**********
The dmc.csv file and command.txt file are required to run this program properly. Please do not modify the dmc.csv file.
You must load the image first before you can apply any operations in the following list (2-9)
For the command, the file name has to be command.txt this program currently support the following commands in the following syntax:
1. load image:       load imagName
2. save image:       save imageName
3. blur image:       blur                  ****No additional argument****
4. sharpening        image: sharpening     ****No additional argument****
5. greyscale image:  greyscale             ****No additional argument****
6. sepiatone image:  sepiatone             ****No additional argument****
7. reduce:           reduce colorPerChannel
8. pixelation:       pixelation blockNum
9. pattern:          pattern outputName
****************************'
Please do not put any empty line in the command.txt file
5. Image Citation
Images 48.jpg and spam.jpg that are in the res folder are photos taken by me. I have authorized the use
of these two images in the class cs 5010
The link for starbucks.jpg is listed here, and the use of the starbucks logo if only for educational 
purpose.
https://www.shutterstock.com/search/starbucks+logo

6. Design/Model Changes
In the original design, I decided to use 2 seperate interface for pixelation and generate pattern. In the
revised design, I decided to use only 1 interface called pixelation beacause the 2 operations shares many
same methods and in fact the pattern generation is built on the pixelated result of the image.
Also, I applied the controller in order to run the script. My controller consist 3 parts: the controller, 
image model and command. In order to avoid using long switch statement, I used a hash map to map the corresponding
operation to the corresponding command object.

7. Assumptions:
The users has to have both command.txt file and dmc.csv file in order to run the program properly.
User must use the load command first before they can process the image.

8. Limitations:
All the requirements from the assignment description have been delivered.


Example Output:
Original Picture: Harley Davidson 1200

![example-part3](https://user-images.githubusercontent.com/71152764/127963484-29219bc6-a774-41fb-abda-bd7cfd22f50a.png)

