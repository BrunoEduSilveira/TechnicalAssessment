# Feefo:  Technical Assessment - Object Orientated Programming

Implemented API which has a functionality to normalize Job Titles process regarding technical assessment.

-------------------
## • About Project
- API Rest
- JDK: Java BellSoft Liberica version 17.0.5
- Gradle version 7.4.1
-------------------
## • Setup
### Project Structure Configuration
![account overview design](./pics/project-structure.png?raw=true)
______________
### Run/Debug Configuration
![account overview design](./pics/run-debug-configuration.png?raw=true)
______________
## Project Architecture

![account overview design](./pics/project-architecture.png?raw=true)

-------------------
## API Endpoints

### Get Normalized Job
**GET**_.../feefo/jobtitle/normalizer_

Get a normalized job title by which title was sent to this endpoint
send a JSON body like this example

```JSON
{
  "title":"Java Engnieer"
}
```
-------------------
### Get All Title Jobs
**GET**_.../feefo/jobtitle_

Get all job list set on project 

just send the request with no params, headers, or body