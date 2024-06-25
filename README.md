# To Do List App
To Do List App

This repository contains a ToDoList Android application developed using Java. The app follows the MVVM architecture pattern and utilizes Data Binding for UI updates and Room Database for local data storage.


Features

Add new tasks with title and optional description.

Edit existing tasks.

Delete tasks.

View a list of all tasks in a RecyclerView.


Architecture

The app is built using the MVVM (Model-View-ViewModel) architecture, which separates the UI, business logic, and data layers. This promotes a clean and maintainable codebase:
Model: Represents the data and business logic (e.g., Task entity, Repository).
View: UI components responsible for displaying data and capturing user input.
ViewModel: Acts as a bridge between the Model and the View, handling the UI-related logic.


Libraries Used

Android Architecture Components: Room (for SQLite database), ViewModel, LiveData.

Data Binding: Simplifies the connection between UI components and data sources.

RecyclerView: Efficiently displays a list of data.

Material Components: Provides modern UI components following Material Design guidelines.


Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, feel free to submit an issue or a pull request.
 
