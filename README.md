# Cunit – Unit and Currency Converter

A unit and currency converter that works without Internet connection.

# Libraries used

- JavaFX

# Structure

`JfxMain` – main class to initialise the JavaFX application.
`JfxController` – JavaFX controller that initialises the MainView.fxml scene.
`CurrencyLoader` – REST API with Fixer.io

# Task Separation

Georgi Vasilev – Documentation, Project Setup, Debugging, Polish and Component Linking
Elena Zaharieva – REST API
Blagovesta Blagoeva – Graphical User Interface
Anton Kovachev – Unit Conversion Algorithms, Testing and Application Logic

# Bugs

None found.


# Inner Workings of the Program

**Unit Conversion:** Converting units is very simple.
Simply select an entry from the left column and the right column,
and then enter a value in the left box. Press the button _Convert_ to get the result.
This magic is achieved by our complex and innovative algorithms (the magic is described by comments).

**Currency Conversion:** Converting currency is done in two ways – online and offline conversion.
When the app is opened, if there is access to the Internet, the app downloads the newest currency
definitions and stores them in `cached_rates.txt`.
This file is then read by the constructor of CurrencyLoader (which calls the loadCurrencies method).

**Scene Switching:** The controller has a method called handleButtonAction, which is referenced from
both About.fxml and MainView.fxml scenes. The About scene contains a short about page regarding the app.
The MainView scene is where all the magic happens.