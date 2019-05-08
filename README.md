# Find the longest sub matrix is the one with the most number of 1s.
It is spring boot web application which has public REST API expecting JSON format 2D array of integer (matrix) as an input,application would process the input and return all details about longest sub matrix is the one with the most number of 1s in JSON format

## Getting Started

### Prerequisites

1. Java 1.8 setup
2. MAVEN build tool
3. IDE

## Building Project
Project is MAVEN building tool based, to build project use below command
 mvn clean install

It will give you build of project in jar file (MatrixProcessorApp-1.0.jar) under {location}\MatrixProcessorApp\target\

## Running Project
### IDE
1. Import given MAVEN project in IDE
2. Run main method in com.assignment.matrixprocessorapp.MatrixProcessorApplication class
3. It will start application on PORT 5050

### JAR
1. To execute jar use below command
   java -jar MatrixProcessorApp-1.0.jar
2. It will start application on PORT 5050   
   
## Running the tests
1. With MAVEN,use below command
   mvn clean test
It will generate test case execution report under {location}\MatrixProcessorApp\target\site\jacoco\index.html (to view in browser)

## API Details
**Get Longest longest sub matrix with the most number of 1s**
-------
   Return JSON data about longest sub matrix with the most number of 1s.
   
* **URL**

  /api/vi/longestSubMatrixes

* **Method:**

  'POST'
  
*  **URL Params**
   None
   
* **Data Params**    
   "elementSet":[[1,0, 0, 0, 1],[0, 1, 1, 1,0,0],[0, 1, 1, 1,0,0],[0, 0, 0, 1,0,0]]  
   
* **Success Response:**

  * **Code:** 201 <br />
    **Content:** {
    "x": 1,
    "y": 1,
    "width": 3,
    "height": 2,
    "elementSet": [
        [
            1,
            1,
            1
        ],
        [
            1,
            1,
            1
        ]
    ]
}
           '
 
* **Error Response:**

  * **Code:** 400 Bad Request.<br />
    **Content:** '{ error :"Please provide valid matrix of integers" }' 

## Author
 *** Gaurav Suryawanshi ***
    