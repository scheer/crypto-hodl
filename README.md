# crypto-hodl
Your private and simple crypto portfolio manager

### Design Goals
* Simple and easy to use web application

### Features
* Monitor the worth of your crypto purchases (your portfolio)
* Receive a daily or weekly portfolio report per mail

### Deployment as Java .jar file
Java 11 is required to build and run the application.

Build the application with Maven:

`mvn clean install`

Copy the resulting .jar file from the /target folder to a destination folder of your choice. Start the application with:

`java –jar cryptohodl.jar`

The web application runs on port 8080. After start, enter and test your SMTP settings. Then add your purchases. The content of the created /data folder contains your data and can be backed up and restored.

### Roadmap
* Full i18n for German and English, EUR and USD
* Calculate the ecological impact of the blockchain transactions (in kWh, CO², ...)
* Track which parts of your crypto are on a safe wallet instead on an exchange
* Advice to buy which crypto with marketing slogan
* Reminder mail, if no crypto was purchases for 6 weeks
* Graphical Visualization for portfolio worth development
* Watched coins: get mail alarms, if price is below some value