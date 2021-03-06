# Crypto HODL
Your private and simple crypto portfolio manager

### Design Goals
* Simple and easy to use web application

### Features
* Monitor the worth of your crypto purchases (your portfolio)
* Queries live crypto price data
* Receive a daily or weekly portfolio report per mail

### Build the application
Java 11 and Apache Maven 3 are required to build and run the application.

Build the application with Maven:

`mvn clean install`

### Run the application

Copy the resulting .jar file from the /target folder to a destination folder of your choice. Start the application with:

`java –jar cryptohodl.jar`

The web application runs on port 8080, so point your browser to http://localhost:8080 in order to get started.

### Basic Usage

First, add your crypto purchases. Each purchase consists of a crypto symbol, an amount, the invest and the date. See the Screenshot below.

![purchases](https://user-images.githubusercontent.com/876501/147583290-b9844183-e2ce-4bd5-91d3-4716958bdada.png)

The application will then calculate the current portfolio and its current worth based on your purchases. See the screenshot below, to get an impression.

![portfolio](https://user-images.githubusercontent.com/876501/147582920-701642fe-e36a-4f48-a21c-96bf89b00273.png)

The content of the created /data folder contains your data and can be backed up and restored.

For the mail report to function, you must enter and test your SMTP settings.

### Roadmap
* Full i18n for German and English, EUR and USD
* Calculate the ecological impact of the blockchain transactions (in kWh, CO², ...)
* Track which parts of your crypto are on a safe wallet instead on an exchange
* Advice to buy which crypto with marketing slogan
* Reminder mail, if no crypto was purchases for 6 weeks
* Graphical Visualization for portfolio worth development
* Watched coins: get mail alarms, if price is below some value

### Disclaimer 
I am not liable for the correctness of the aplication nor the actions performed based upon it.
