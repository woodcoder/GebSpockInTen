# Geb / Spock in Ten

How to get up and running with Geb and Spock for website functional testing in
ten steps (or minutes, if you're quick).

## 1. Install Java

Skip this step if you're using a Mac, as it's already done.

Other OSs will need a JDK installed, preferably version 7, and it added to your environment.

Windows
```
> SET JAVA_HOME=C:\Program Files\Java\jdk1.7.0_17
> SET PATH=%PATH%;%JAVA_HOME%\bin
```
Linux
```
$ export JAVA_HOME=/Library/Java/Home
$ export PATH= "$PATH:$JAVA_HOME/bin"
```

## 2. Download Grails

Download version 2.2.1 from http://grails.org and unzip somewhere suitable.

## 3. Set up and create Grails Project

Windows
```
> SET GRAILS_HOME=C:\springsource\grails-2.2.1
> SET PATH=%PATH%;%GRAILS_HOME%\bin
> grails create-app GebSpockInTen
> cd GebSpockInTen
```
Mac/Linux
```
$ export GRAILS_HOME=/Users/username/springsource/grails-2.2.1
$ export PATH="$PATH:$GRAILS_HOME/bin"
$ grails create-app GebSpockInTen
$ cd GebSpockInTen
```

## 4. Add Geb and Spock Dependencies

Edit `grails-app/conf/BuildConfig.groovy`.

Insert into dependencies {} block:
```
        // Geb functional test requirements
        test 'org.gebish:geb-spock:0.9.0-RC-1'
        test 'org.seleniumhq.selenium:selenium-support:2.31.0'
        test('org.seleniumhq.selenium:selenium-firefox-driver:2.31.0') {
            exclude: 'xml-apis'
        }
        test 'org.spockframework:spock-grails-support:0.7-groovy-2.0'
```
Insert into plugins {} block:
```
        // Required to build functional tests
        test ':geb:0.9.0-RC-1'
        test(':spock:0.7') {
            exclude 'spock-grails-support'
        }
```

## 5. Install Firefox

Download and install from http://www.mozilla.org . I'm using version 19.0.2.

## 6. Make a Geb Config file

Create a `test/functional` folder.

Create a new file `test/functional/GebConfig.groovy` with the following in it:
```
import org.openqa.selenium.firefox.FirefoxDriver

driver = { new FirefoxDriver() }
```

This tells Geb we're going to use Firefox.  It's here you can tell it to use
Chrome, Safari, IE, etc. too -- or switch them using command line settings
(note that you will need to add the appropriate selenium dependencies into the
BuildConfig too if you do that).
