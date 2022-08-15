# FTP Server App

### App that works with build-in FTP server. 

FTP Server has JSON file (*students.json*) that stores students \
by id and names. App can write and read JSON from server.\

#### Basic functions for app is: 
1. Print list of all students
2. Find student by id
3. Add new student
4. Delete student by id
5. Close program and server

Basic folder for FTP Server and FTP Local are:\
***E:/FTPServer** and **F:/FTPLocal*** \
However, this could be changed in code.
___
### Instructions

App is compiled and build using gradle. All external libs are already \
included into the build, no downloads besides JDK 8 is needed. \
\
To run executable jar in **build/libs** folder use following command:
```
java -jar FTPServer-1.0-SNAPSHOT.jar