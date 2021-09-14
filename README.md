# Gittigidiyor Java Spring Bootcamp Fifth Homework

## How to Run?

* Clone this repository
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar -Dspring.profiles.active=test target/dev-patika-app-app-0.0.1.war
or
        mvn spring-boot:run -Drun.arguments="spring.profiles.active=test"
```
* Check the stdout or boot_example.log file to make sure no exceptions are thrown

Once the application runs you should see something like this

```
2021-09-05 17:31:23.091  INFO 19387 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer  : Tomcat started on port(s): 8080 (http)
2021-09-05 17:31:23.097  INFO 19387 --- [           main] dev.patika.app.AppApplication             : Started Application in 22.285 seconds (JVM running for 23.032)
```

## About the Service

### Student Services

#### Create a Course Resource
```
POST /api/student
Accept: application/json
Content-Type: application/json

{
  "fullName": "Sezgin Öztürk",
  "address": "Avcılar/İstanbul",
  "birthDate": "1996-10-24",
  "gender": "Erkek"
}

RESPONSE: HTTP 200 (Created)
```

#### List Students Resource
```
GET /api/students
Content-Type: application/json

RESPONSE: HTTP 200
[
  {
    "id": 1,
    "createdDate": "2021-09-07T13:41:52.835Z",
    "fullName": "Sezgin Öztürk",
    "birthDate": "1996-10-24",
    "address": "Avcılar/İstanbul",
    "gender": "Erkek"
  },
  {
    "id": 2,
    "createdDate": "2021-09-07T13:41:52.835Z",
    "fullName": "Sezgin Öztürk",
    "birthDate": "1996-10-24",
    "address": "Avcılar/İstanbul",
    "gender": "Erkek"
  }
]
```

#### Get a Student by Student Id Resource
```
GET /api/students/{id}
Accept: application/json
Content-Type: application/json

RESPONSE: HTTP 200
{
    "id": 2,
    "createdDate": "2021-09-07T13:41:52.835Z",
    "fullName": "Sezgin Öztürk",
    "birthDate": "1996-10-24",
    "address": "Avcılar/İstanbul",
    "gender": "Erkek"
    }
```

### Instructors Services

#### Create a Instructor Resource
```
POST /api/instructors
Accept: application/json
Content-Type: application/json

{
  "fullName": "Koray Güney",
  "address": "Pendik/İstanbul",
  "phoneNumber": "+905554443322"
}

RESPONSE: HTTP 200 (Created)

RESPONSE: HTTP 400
{
  "statusCode": 400,
  "message": "Instructor(Koray Güney) with Phone number(+905554443322) already exists!",
  "timestamp": 1631022507557
}
```

#### List Instructors Resource
```
GET /api/instructors
Content-Type: application/json

RESPONSE: HTTP 200
[
  {
    "id": 1,
    "createdDate": "2021-09-07T13:36:27.814Z",
    "fullName": "Koray Güney",
    "address": "Pendik/İstanbul",
    "phoneNumber": "+905554443322",
    "courses": [
      {
        "id": 2,
        "createdDate": "2021-09-07T13:36:35.222Z",
        "name": "Java Programlama",
        "code": "JAVA",
        "creditScore": 5,
        "students": []
      }
    ]
  }
]
```

#### Get a Instructor by Instructor Id Resource
```
GET /api/instructors/{id}
Content-Type: application/json

RESPONSE: HTTP 200
{
    "id": 1,
    "createdDate": "2021-09-07T13:36:27.814Z",
    "fullName": "Koray Güney",
    "address": "Pendik/İstanbul",
    "phoneNumber": "+905554443322",
    "courses": [
      {
        "id": 2,
        "createdDate": "2021-09-07T13:36:35.222Z",
        "name": "Java Programlama",
        "code": "JAVA",
        "creditScore": 5,
        "students": []
      }
    ]
}
```

#### Instructor Sallary Updated Log

```
GET /api/logs/get-all-by-instructor-id-or-date
GET /api/logs/get-all-by-instructor-id-or-date?dateTime=14/09/2021
GET /api/logs/get-all-by-instructor-id-or-date?instructorId=1
GET /api/logs/get-all-by-instructor-id-or-date?dateTime=14/09/2021&instructorId=1
Content-Type: application/json

RESPONSE 200
[
  {
    "id": 1,
    "instructorId": 1,
    "salaryBefore": 250,
    "salaryAfter": 300,
    "percent": 20,
    "percentType": "SUM",
    "clientIpAddress": "0:0:0:0:0:0:0:1",
    "clientUrl": "/api/instructors/updateSalary",
    "sessionActivityId": "DDC0F37E537DCB336DD61D83033E6EF3",
    "logDate": "2021-09-14"
  },
  {
    "id": 2,
    "instructorId": 2,
    "salaryBefore": 10500,
    "salaryAfter": 13650,
    "percent": 30,
    "percentType": "SUM",
    "clientIpAddress": "0:0:0:0:0:0:0:1",
    "clientUrl": "/api/instructors/updateSalary",
    "sessionActivityId": "DDC0F37E537DCB336DD61D83033E6EF3",
    "logDate": "2021-09-14"
  },
  {
    "id": 3,
    "instructorId": 1,
    "salaryBefore": 300,
    "salaryAfter": 30,
    "percent": 10,
    "percentType": "SUB",
    "clientIpAddress": "0:0:0:0:0:0:0:1",
    "clientUrl": "/api/instructors/updateSalary",
    "sessionActivityId": "DDC0F37E537DCB336DD61D83033E6EF3",
    "logDate": "2021-09-14"
  }
]
```

### Course Services

#### Create a Course Resource
```
POST /api/courses
Accept: application/json
Content-Type: application/json

{
  "code": "JAVA",
  "creditScore": 5,
  "instructorId": 1,
  "name": "Java Programlama"
}

RESPONSE: HTTP 200 (Created)
```

#### List Courses Resource
```
GET /api/courses
Content-Type: application/json

RESPONSE: HTTP 200
{
    "id": 2,
    "createdDate": "2021-09-07T13:36:35.222Z",
    "name": "Java Programlama",
    "code": "JAVA",
    "creditScore": 5,
    "students": []
}
```

#### Get a Course by Course Id Resource
```
GET /api/courses/{id}
Accept: application/json
Content-Type: application/json

RESPONSE: HTTP 200
[
  {
    "id": 2,
    "createdDate": "2021-09-07T13:36:35.222Z",
    "name": "Java Programlama",
    "code": "JAVA",
    "creditScore": 5,
    "students": []
  }
]
```

#### Add Students To the Course Resource
```
PUT /api/courses/add-student-to-course?courseId={courseId}&studentId={studentId}
Accept: application/json
Content-Type: application/json

RESPONSE: HTTP 200(Updated.)
```


