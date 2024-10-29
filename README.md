# spring-batch
This File will guid you to run the application with module wise.
## Spring-batch-chunk.
you can clone the project and do maven build on your local and use the below url to run the job.

  [Url to test first spring batch](http://localhost:8080/api/job/start/myChunkjob)

## Spring-batch-chunk-datasource.
you can clone the project and do maven build on your local and use the below url to run the job.
#### To Read CSV file and wrtie in CSV file use below URL:
 
  - [Url to Read CSV file and wrtie in another CSV file](http://localhost:8080/api/job/start/csv/csvChunkjob)

in this code i have added student-with-error.csv file which is containing the worng value at id 7. so while running with that file you can observer that it will read the 6 record successfully and then it will give parsing error. In below section you can find the solution of that.
    
#### To Read JSON file and wrtie in JSON file use below URL:

  - [URL to Read JSON file and wrtie in another JSON file](http://localhost:8080/api/job/start/json/jsonChunkjob)

   
#### To Read XML file and wrtie in XML file use below URL:

  - [URL to read from XML file and wrtie in another XML file ](http://localhost:8080/api/job/start/xml/xmlChunkjob)
   

#### To Read XML file and wrtie in XML file use below URL:

  - [URL to read from CSV and write to MySql Db ](http://localhost:8080/api/job/start/csv_to_jdbc/csvToJdbcChunkjob)

 
