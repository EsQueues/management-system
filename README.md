# Library Management System
### SE-2208
### Sayat Kusain, Zagipa  Koishibayeva, Almas Amrenov
## Project Overview
### Description:
The project represents the initial structure of a simple console-based Library Management System (LMS) implemented in Java. The system aims to facilitate the management of literature, readers,
and associated tasks within a library environment.
### Idea of the Project:
The Library Management System is designed to automate and streamline various aspects of library operations. It includes functionalities such as adding literature, signing up readers, assigning readers to books, 
releasing books, and generating analyses for library workers. 
### Purpose of the Work:
The purpose of this project is to create an efficient and user-friendly tool for librarians to manage their library resources and operations. The system aims to reduce manual efforts in handling tasks
like book management,
reader registration, and data analysis, thereby improving overall library efficiency.
## Main body
### Decorator pattern
The Decorator pattern is implemented to extend the functionality of the UserObserver class by introducing the NotificationDecorator interface and the TimeStampDecorator 
concrete decorator. The NotificationDecorator interface serves as the base decorator, extending the existing UserObserver interface. The TimeStampDecorator class, a concrete decorator, encapsulates
a reference to the original UserObserver instance and enhances its behavior by adding a timestamp to the notification messages through the handleEvent method. This approach enables the dynamic augmentation
of notification messages without altering the structure of the original UserObserver or its implementing classes. 
![image](https://github.com/EsQueues/management-system/assets/122588120/30933bd2-9de9-4fec-9fe9-298da424052a)

### Observer pattern
The Observer pattern is implemented through the LibraryNotificationSystem, NotificationSystem interface, and UserObserver interface. The LibraryNotificationSystem serves as the subject,
maintaining a list of registered observers (implementing the UserObserver interface). Observers can be added or removed dynamically, and when the state of the subject changes, it notifies all observers by
calling their handleEvent method. This design facilitates a decoupled relationship between the subject and observers, allowing for flexibility and extensibility in handling events. Concrete observers can 
define specific behaviors in response to notifications, promoting a modular and scalable architecture within the context of a library notification system.
![image](https://github.com/EsQueues/management-system/assets/122588120/5ee97b9d-1bf3-479e-b800-e3dcd3486f9c)

### Strategy Pattern
The Strategy pattern is employed to encapsulate different notification strategies by defining a family of algorithms, enabling them to be interchangeable. Specifically, two concrete strategies, 
NewBookObserver and ReleasedBookObserver, implement the UserObserver interface, providing distinct behaviors for handling notifications of new book additions and released books, respectively. 
The NotificationType enum serves as a set of strategies, representing the different types of notifications that observers can handle. Additional notification strategies can be easily added by
implementing the UserObserver interface with different behaviors based on the notification type,
demonstrating a clear separation between the notification strategies and the objects that use them. This approach allows for dynamic selection and switching of strategies at runtime, 
providing a modular and maintainable solution for handling various types of notifications in a library system.
![image](https://github.com/EsQueues/management-system/assets/122588120/67cda32a-0634-4edc-9515-18c3906a5fb5)

### Factory Pattern
In the BookController class, the Factory Method pattern is implemented through the createLiterature method, which serves as the factory method responsible for creating instances of the Book class. 
This method collects user input for book details, such as title, year, author, and genre, and then instantiates a new Book object with the provided information. The Factory Method pattern allows for flexibility
in creating Book instances, facilitating potential extensions or variations in the creation process. Additionally, the class utilizes the Observer pattern by notifying the libraryNotificationSystem about the 
creation of a new book, demonstrating a modular and loosely coupled design for managing book-related functionalities.
![image](https://github.com/EsQueues/management-system/assets/122588120/78d6c323-4003-4b55-a310-71f1505d74e3)
![image](https://github.com/EsQueues/management-system/assets/122588120/42f503c0-f02a-4e9e-a2da-f0e3f97be175)

### Adapter Pattern
The Adapter pattern is implemented through the ListToExcelAdapter class, which acts as a bridge between the existing BookDAO interface and the functionality of exporting
book data to an Excel file. The ListToExcelAdapter adapts the interface of the BookDAO to make it compatible with the requirements of creating an Excel workbook and populating it with book data.
By encapsulating the interactions with the BookDAO within the adapter, the existing codebase can seamlessly work with the external Excel export functionality without requiring modifications to its
original interface.
![image](https://github.com/EsQueues/management-system/assets/122588120/8e41c1ba-9286-42cb-863b-948192edc09b)

### Singleton Pattern
The DatabaseConnection class exemplifies the Singleton pattern by ensuring that only one instance of the class exists, providing a global access point to the database connection throughout the application.
The private constructor restricts direct instantiation, and the static instance variable holds the single instance. The getInstance method follows a lazy initialization approach, creating a new instance
only if the current instance is null or the connection is closed.
![image](https://github.com/EsQueues/management-system/assets/122588120/690dcc39-02a1-40a9-a71f-784ad5226c40)

## Conclusion
### Key points of the project
The project involves the development of a Library Management System implemented in Java, incorporating several key features. The system employs the Observer pattern to notify users about events such
as new book additions and book releases through the LibraryNotificationSystem and various UserObserver implementations. Additionally, the Strategy pattern is utilized for different notification types,
exemplified by the NotificationType enum and concrete observer implementations like NewBookObserver and ReleasedBookObserver. The Decorator pattern is applied to enhance the UserObserver class by introducing
the NotificationDecorator interface and the TimeStampDecorator concrete decorator, allowing the dynamic addition of timestamps to notification messages. The Factory Method pattern is employed in
the BookController class, facilitating the creation of book instances. The Adapter pattern is used in the ListToExcelAdapter class to bridge between the existing BookDAO and the functionality
of exporting book data to an Excel file.
### Project outcomes
The project successfully implements a modular and extensible Library Management System, providing functionalities for book creation, deletion, and assignment, as well as notifying users about library events.
### Challenges faced
Challenges during the project may have included ensuring proper integration of design patterns, handling potential concurrency issues in a multi-threaded environment, and addressing any specific
requirements for user input validation or error handling.
### Future improvements
Future improvements could focus on refining user interfaces, enhancing error handling, and implementing more comprehensive validation mechanisms. Additionally, the system could benefit from the
incorporation of a database for persistent data storage, providing a more robust solution for managing library information. Further modularization and abstraction could be applied to promote greater
flexibility and scalability.

