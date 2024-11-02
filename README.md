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

 
#### To Read MySql data and wrtie in csv file use below URL:

  - [URL to read from MySQL and write to CSV ](http://localhost:8080/api/job/start/jdbc_to_csv/JdbcToCsvChunkjob)

#### To Read MySql data and wrtie in json file use below URL:

  - [URL to read from MySQL and write to JSON ](http://localhost:8080/api/job/start/jdbc_to_json/JdbcToJsonChunkjob)

#### To Read MySql data and wrtie in xml file use below URL:

  - [URL to read from MySQL and write to XML ](http://localhost:8080/api/job/start/jdbc_to_xml/JdbcToXmlChunkjob)

#### To Read Json file data and wrtie in Mysql use below URL:

  - [URL to read Json file data and wrtie in Mysql use below URL](http://localhost:8080/api/job/start/json_to_jdbc/JsonToJdbcChunkjob)

 #### To Read Xml file data and wrtie in Mysql use below URL:

  - [URL to read Xml file data and wrtie in Mysql use below URL](http://localhost:8080/api/job/start/xml_to_jdbc/XmlToJdbcChunkjob)   

 #### To Read Xml file data and wrtie in another xml file with Async job use below URL:

  - [URL to read Xml file data and wrtie in another xml file with Async job use below URL](http://localhost:8080/api/job/start/async/xmlChunkjob)

Added a functionality to crearte a scheduled spring batch job which will autometically run based on the scheduled configuration.

## Spring-batch-tasklet.

#### This job contains the functionlaity to merge the two job. 1 is tasklet type and 2nd is of chunk type.
- [Use below Url to test the JOB](http://localhost:8080/api/job/start/firstJob)

#### Tasklet job to read CSV file from input/csv directory, Process item to make Uppercase and then write in output/csv directory.
##### Note: you can use same code to read the txt file as well. to test with txt fileReader and writer pls enable txt code from reader and writer class.
- [Use below Url to Read, Process, Write using tasklet](http://localhost:8080/api/job/start/tasklet/csv/csvTaskletJob)

#### Tasklet job to read Json file from input/json directory, Process item to make Uppercase and then write in output/json directory.
- [Use below Url to Read, Process, Write using tasklet](http://localhost:8080/api/job/start/tasklet/json/jsonTaskletJob)


#### Tasklet job to read Xml file from input/xml directory, Process item to make Uppercase and then write in output/xml directory.
- [Use below Url to Read, Process, Write using tasklet](http://localhost:8080/api/job/start/tasklet/xml/XmlTaskletJob)
