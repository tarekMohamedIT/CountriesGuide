# CountriesGuide
A simple countries information guide using an API and Firebase authentication.

![flag_logo_round](https://user-images.githubusercontent.com/28806543/47190334-1268e780-d341-11e8-9ea1-6158460dc2cb.png)

This project aims to simplify how android clean architecture works.

The Java part is separated into 3 parts :

1) The core : the layer where the models, interfaces and events are initialized.

2) The interactors : The classes which deals with the background tasks(managers), This layer contains the classes which work in the background then deliver it's results to the UI to be viewed.

3) The UI : Where the Activity classes and adapters exists, This layer is for the UI thread where the data is brought from the interactors to be displayed.
