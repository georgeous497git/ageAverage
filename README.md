# Age Average Calculation (Assessment #1)

## Description
🤖 The Age Average Calculation project is a tool oriented to do the average calculation. 🤖

A List of Strings & Numbers should be provided to perform the calculation meeting the following conditions.
- The list of **names** & **ages** should have the same lenght.
- The format of the file should meet the YAML syntax.
- The file should meet the name **inputData.yml** within the folder named **resources**

Example of input data:
```sh
input-data:
  data:
    - names: ["Hardwell", "Martin Garrix", "Junkie XL", "Armin van Buuren", "Tiësto", "Oliver Heldens"]
      ages:  [34, 26, 54, 45, 53, 27]
```

##### Fault Tolerance

The project is able to skip the registers that have not the necessary data.

Example of bad input data:
```sh
input-data:
  data:
    - names:
      ages:
    - names: "Jorge"
      ages:
```
## Requirements
- Gradle Build Tool (7 or greater)
- IDE to execute the code (IntelliJ or Spring Tool Suite)

## Execution
1 - Create a folder where you want to download the project. Enter to the folder created.
2 - Clone the project into your local machine.
```sh
git clone git@github.com:georgeous497git/ageAverage.git
```
2 - Once you downloaded the project execute the following command. (The project is already configured with basic configuration.)
```sh
./gradlew bootRun
```
3 - In the console a banner will be printed (AGE AVERAGE).
4 - To validate the result, the project will create two files within the **classpath** with the name **outputData.txt** and **ageAverage.log** respectively. 
