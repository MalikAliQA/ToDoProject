Coverage: 875%
# ToDoProject

A TODO web application that allows people to add users and tasks and link tasks to users with full CRUD functionality for both users and tasks

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites
 
1. Make sure you have Maven installed 
2. Make sure you have a Java Development Environment installed 
3. Make sure you have Mysql workbench installed or a GCP SQL instance 
4. Make sure you have SpringToolSuite4 installed 


### Installing

1. Fork
2. clone respository you created onto your local machine using
3. Create your required branches for development
4. Open SpringToolSuite
5. Open the project as a Maven project
6. Run project as a SpringBoot App 
7. REMEMBER TO CHANGE THE IP ADDRESS TO MATCH YOUR GCP INSTANCE in the application-dev.properties which is in src/main/resources
```
jdbc:mysql://ipaddressofinstance/databasename 
```


 To run project as is:

You will first need to create a database on your GCP instance using:
```
CREATE DATABASE databasename;
```
Run the project either from the Spring Tool suite or from the command line, the fat jar file is in the documention folder.
Once up and running open a web browser, and type in: localhost:9092/index.html, this will display the homes page of the application
from there you can use the nav bar for the functionality, There should already be some data added for you



## Running the tests

To run the tests righ click on the root directory in spring tool suite and click run as JUnit test, to get the coverage click coverage as JUnit test

### Unit Tests 

Unit tests these test whether the correct code is ran in a method when data is entered 
e.g. To test the create functionality 

```
@Test
	void createTest() throws Exception {
		when(this.service.create(TEST_TASK_1)).thenReturn(this.mapToDTO(TEST_TASK_1));
		assertThat(new ResponseEntity<TaskDto>(this.mapToDTO(TEST_TASK_1), HttpStatus.CREATED))
				.isEqualTo(this.controller.create(TEST_TASK_1));
		verify(this.service, atLeastOnce()).create(TEST_TASK_1);

	}
```

give the data the stored values in TEST_TASK_1 to the method create and creates a task based off that data then the asserEquals says is the dummy data you supplied the same as creating a task when the DTO.create method is ran with the dummy data 

### Integration Tests 
These were the mockito tests done on the controller classes creating new data to see if the class is running its method properly testing the task input in a sense 
e.g. for the TaskController testing the create method 

```
@Test
	void createTest() throws Exception {
		TaskDto testDTO = mapToDTO(new Task("Shopping List", "Oranges"));
		String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);
		RequestBuilder request = post(URL + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

		ResultMatcher checkStatus = status().isCreated();

		TaskDto testSavedDTO = mapToDTO(new Task("Shopping List", "Oranges"));
		testSavedDTO.setId(5L);
		String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);
		ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}
```
Creates a new task based on the data we are passing it, then puts it in JSON format, and sends the request to the url specified
next it saves the test data and puts the saved data in string format too 
the last line checks if the data we sent to the request is the same as the data we created

### Front End Tests

Front end testing is done in Eclipse with Selenium where we automate the navigation and input process that a user would do on the front end, This is in the linked repo called ToDoSeleniumTesting, The testing is in reference with The POM Design patter, so each page has its own class in the pages folder and the testing in a seperate folder below is the code for the create a task page 

```
@Test
	public void createTaskTest() throws Exception {
		LOGGER.warning("connecting to ToDo Site");
		HomeNav webpage = PageFactory.initElements(driver, HomeNav.class);
		CreateTask task = PageFactory.initElements(driver, CreateTask.class);

		
		driver.get(HomeNav.URL);
		Helper.snapShot(driver, "src/test/resources/reports/createtask/shot1.png");
		webpage.navReadAllTaskPage();
		Helper.snapShot(driver, "src/test/resources/reports/createtask/shot2.png");
		LOGGER.warning("Clicks add a task link");
		webpage.navCreateTaskPage();
		Helper.snapShot(driver, "src/test/resources/reports/createtask/shot3.png");
		LOGGER.warning("Enter info and click submit");
		task.createaTask("1", "To Buy", "Cake");
		Helper.snapShot(driver, "src/test/resources/reports/createtask/shot4.png");
		
		LOGGER.info("Finished");
		 boolean expected;
	     expected = true;
	     Boolean result = driver.getPageSource().contains("1");
	     System.out.println(result);
	
	       
        assertEquals(expected, result);
        
    	if ((expected = true) && (result = true)) {
			test.log(LogStatus.PASS, "Success created task");
		} else {
			test.log(LogStatus.FAIL, "Failed");
		}
    	
    	webpage.navReadAllTaskPage();
		Helper.snapShot(driver, "src/test/resources/reports/createtask/shot5.png");
		
	}
```

You can follow the screenshots to see how the testing is carried out, each page has its own folder of screenshots 

## Deployment

To deploy you have to create a Jar file using maven 

Right click on the root directory called TodoProject, go to Run As, and click Maven Build... in the Goals input field 
type:
```
clean package
```
click apply and then click run

clean just incase there is already a target folder and package to create a jar file
once completed this will create a new folder called target and within that folder there will be the jar file required to run the project from the command line 



## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Eclipse](https://www.eclipse.org/) - Java IDE
* [GCP MySQL Instance](https://cloud.google.com/) - MySQL instance on the Google Cloud Platform
* [Spring](https://spring.io/tools) - Java IDE


## Authors

* **Malik Ali** - *Finalisation*  - [Malik Ali](https://github.com/MalikAliQA)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Vinesh Ghela and Alan Davis for answering all my questions no matter how simple or complex they were
* All previous QA instructors that have helped me brush up on my Java Knowledge
