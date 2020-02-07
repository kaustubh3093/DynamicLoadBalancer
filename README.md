# DynamicLoadBalancer
The goal of the Project is to allocate a machines on the server and finally add the services on the respective machines finally using round robin get the service hosted on the machine you want. The functionality includes adding machines to the server, adding service to the machines and if the machine is not present for that specific service throw an error. Also, you can delete the machine which in turn delete all the service on that machine and you can delete the service.

-----------------------------------------------------------------------
-----------------------------------------------------------------------
Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in dynamicLoadBalancer/src folder.

1)Navigate to the src folder in the linux
-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run: Please change the file path as per location

####Command: ant -buildfile build.xml run -Darg0="/home/kaustubh/Documents/dynamicLoadBalancer/src/input.txt" -Darg1="/home/kaustubh/Documents/dynamicLoadBalancer/src/output.txt"

Note: Arguments accept the absolute path of the files.

-----------------------------------------------------------------------
## Description:
1) Driver class calls an method in FileProcessor class which reads the line from the input file one at a time and process it
2) If any line has less number of argument or it is empty it will print the result in output file and skip processing that line and moves to new line. The reason of processing one line at a time is in the case when input file is large then storing all in an data structure will waste a lot of memory so processing one line at a time will be fast.
3) If the input file is empty the same will get printed in the output file and program stopped.
4) Given the line in the input file is correct then corresponding method called in cluster class
5) Once the method in cluster class return true means it process the line correctly without any error then it call update method of loadbalancer which will update the trie datastructure.
6) When Request comes in input file then cluster calls request method of loadbalancer which will fetch an value from the trie data structure on round robin logic and print the result in output file using the method written in Results.java class.


