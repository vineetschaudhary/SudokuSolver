#Synopsis
The API is used to solve Sudoku puzzle. It take a parameter board with values as given below. It has x as the values which needs to be filled.
<br>
"x,x,x,2,6,x,7,x,1,6,8,x,x,7,x,x,9,x,1,9,x,x,x,4,5,x,x,8,2,x,1,x,x,x,4,x,x,x,4,6,x,2,9,x,x,x,5,x,x,x,3,x,2,8,x,x,9,3,x,x,x,7,4,x,4,8,9,5,x,x,3,6,7,x,3,x,1,8,x,x,x"

#Code
<br>
http://localhost:9000/sudokuSolver?board=x,x,x,2,6,x,7,x,1,6,8,x,x,7,x,x,9,x,1,9,x,x,x,4,5,x,x,8,2,x,1,x,x,x,4,x,x,x,4,6,x,2,9,x,x,x,5,x,x,x,3,x,2,8,x,x,9,3,x,x,x,7,4,x,4,8,9,5,x,x,3,6,7,x,3,x,1,8,x,x,x
<br>
One can also test it using webservice testing tools like Postman, which is easily available as Chrome plugin. 

Technology stack used: <br>
1. Spring Boot <br>
2. Spring Rest Services <br>
3. Spring social<br>
3. Java 8<br>
4. Maven.<br>

It has SudokuController, which is the entry point for this service. It serves the request with path = "/sudokuSolver" means whenever request is made using /sudokuSolver it will be processed by this controller. Request is then forwarded to ResultBuilder, which has methods to serve the purpose. You can visit to the class and check the comments or code which is simple and easily understandable.
<br>
It has different DTOs to hold the input and output data. Valid input is checked and passed to the ResultBuilder class and result is mapped in the Result class and then returned which is automatically transformed as json from inbuild jackson in spring.
<br>
In case puzzle is not solved it will be bind to the Error object and returned with message “Cannot be completed”.
<br>
It logs the debugging data to the sudokuSolver.log file initially but it can be changed in application.properties file by using property logging.path property.
<br>
GlobalExceptionHandler is used as ControllerAdvice which process all the exceptions thrown in the application. It has some specific handler methods to process the exception and one method with Exception in case no handler is present for the exception thrown.
<br>

#Installation and Running Application<br>

You should have java 8 installed on your system.<br>

Repository contains the jar file, which can be run from command prompt using above command. Please check that you have rights on the directory where jar is placed.<br>

By default ports are given as server.port: 9000 and management.port: 9001.<br>

In case ports are already in use we can send those directly using command line argument.<br>

If you start it from command line and it doesn’t start means you have system rights problem so place it somewhere else. In case it started with printing something on CMD and again stopped then check logs sodukoSolver.log.<br>
java -jar sudoku-solver-1.0-SNAPSHOT.jar<br>

You may also use --debug to check application logs.<br>
java -jar sudoku-solver-1.0-SNAPSHOT.jar --debug<br>

You may also use --trace option to check all the logs from Spring mvc like parameter, request specific logs.<br>
java -jar sudoku-solver-1.0-SNAPSHOT.jar --trace<br>

In case ports are already used then use above command to specify port. <br>
java -jar -Dmanagement.port=9991 -Dserver.port=9992 sudoku-solver-1.0-SNAPSHOT.jar<br>

Code can be imported to any IDE and you can use Maven to build and package the project. You can also use command prompt and use maven to build and package project.<br>

#Tests<br>

There are integration and unit testcases to test the functionality. SudokuApplicationIntegrationTests is the class which contains all the integration tests and SudokuResultBuilderTest contains unit testcases.


