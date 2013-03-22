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
