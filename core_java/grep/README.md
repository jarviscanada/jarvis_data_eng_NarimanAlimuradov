# Java Grep Tool

## Introduction
Filtering through text to find key words is time consuming, and it gets even more difficult if the files contain thousands of lines of text. The Java Grep Tool aims to provide a modular and simple way to allow a user to search through several files for lines containing specific words.
Developed using Java with the Maven project management tool. Utilizes Java streams for efficiency and packaged using Docker.

## How do I use it?
How to use your apps?
To use my app, simply pull the docker image from DockerHub, using:
docker pull nalimuradov/grep

Next, just start a container from that image with the three parameters required:
docker run nalimuradov/grep regex rootPath

The regex argument will be the regular expression you're looking for.
The rootPath argument is the path of the directory that contains all the files you want to read from.
The outFile argument is the name of the file that will store the results.

## Implemenation
First, we initialize a list of Strings that will store all matching lines. These will be the results returned to the user.

Next, we iterate through each file in the specified directory.

For each file, we look through each line and check if it contains the regular expression given.

If it does, we append it to the list containing the results.

Finally, we write all the results to an output file that is saved in the data directory.

## Performance Issue
When dealing with large files, Java will try to allocate all the memory required beforehand. If this exceeds the limit specified, it will crash the app. To overcome this challenge, streams were used instead of loops so that only what is currently needed is stored and then cleared when we're done with it. This solution allows the application to perform the same task with a fraction of the memory.

## Testing
Testing was performed manually on sample text files containing varying amounts of text. Results are then saved to an output text file. 

As an example, the complete works of Shakespeare were placed into a text file and used as training data. 
If I use the regex: .\*Gutenberg.\*, it should match any lines in the file that have the word Gutenberg in it.
Below are the results: 

![Gutenberg Example](assets/gutenberg.png)

## Deployment
How you dockerize your app for easier distribution?

The app was deployed as a DockerFile on DockerHub. Maven was used to package the project and a Docker image was created locally with the help of the Maven shade plugin, which packaged all the dependencies required to run it. 
The image can be pulled with docker pull nalimuradov/grep and the container ran with 
docker run nalimuradov/grep regex rootPath outFile

## Future Improvements
1. Provide more clarity if file paths may be a problem. For example, output the path of the output file to console so that the user knows where to look, or mention the directory the program is currently looking in if the input files cannot be found.
2. Provide an option to work with nested directories. As of right now, it will only look through the files directly in the directory specified, and it will not go deeper into sub-directories that may have more files.
3. 
