# MacroMunchers
Find foods that match your calorie range and macro ratios.

Part 1: Data Collection 
- Implemented a web crawler to collect 5GB of data 
- Data consists of only food related websites that contain nutritional information

Part 2: Hadoop Data Processing and Store into Cassandra
- Parsed all the nutritional values from the HTML files collected from Part 1 for each food item.
- Used Hadoop to process data for macro ratios for each of the following: 
  - Calories
  - Protein 
  - Fats 
  - Carbs
- The values generated from Hadoop are stored in four different tables in Cassandra. 

Part 3: Web Interface
- Built Web interface to explore the preprocessed or analyzed data
- Created web pages using PHP and HTML/CSS/JavaScript for each of the following (displayed by a Web Server (XAMPP)): 
  - Homepage
  - Calories
  - Macros

Screenshots: 
- Homepage 
  - <img width="539" alt="screen shot 2016-07-24 at 12 21 54 pm" src="https://cloud.githubusercontent.com/assets/10494511/17085918/7f2ddd02-5199-11e6-8035-69dca89cb4a6.png">

- Calories
  - <img width="539" alt="screen shot 2016-07-24 at 12 22 07 pm" src="https://cloud.githubusercontent.com/assets/10494511/17085923/965a70a8-5199-11e6-962f-eefaf79f05d8.png">

- Macros 
  - <img width="525" alt="screen shot 2016-07-24 at 12 22 22 pm" src="https://cloud.githubusercontent.com/assets/10494511/17085925/a8b3c74a-5199-11e6-8d0b-fcb5ffc5c737.png">
