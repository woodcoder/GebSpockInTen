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

Download version 2.2.4 from http://grails.org and unzip somewhere suitable.

## 3. Set up and create Grails Project

Windows
```
> SET GRAILS_HOME=C:\[path-to-where-you-put-grails]\grails-2.2.4
> SET PATH=%PATH%;%GRAILS_HOME%\bin
> grails create-app GebSpockInTen
> cd GebSpockInTen
```
Mac/Linux
```
$ export GRAILS_HOME=/Users/[your-username]/[path-to-where-you-put-grails]/grails-2.2.4
$ export PATH="$PATH:$GRAILS_HOME/bin"
$ grails create-app GebSpockInTen
$ cd GebSpockInTen
```

## 4. Add Geb and Spock Dependencies

Edit `grails-app/conf/BuildConfig.groovy`.

Insert into dependencies {} block:
```
        // Geb functional test requirements
        test 'org.gebish:geb-spock:0.9.1'
        test 'org.seleniumhq.selenium:selenium-support:2.35.0'
        test('org.seleniumhq.selenium:selenium-firefox-driver:2.35.0') {
            exclude: 'xml-apis'
        }
        test 'org.spockframework:spock-grails-support:0.7-groovy-2.0'
```
Insert into plugins {} block:
```
        // Required to build functional tests
        test ':geb:0.9.1'
        test(':spock:0.7') {
            exclude 'spock-grails-support'
        }
```

## 5. Install Firefox

Download and install from http://www.mozilla.org . I'm using version 23.0.1.

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

## 7. Create a simple Geb Page definition

Create new file `test/functional/GoogleHomePage.groovy` with:
```
import geb.Page

class GoogleHomePage extends Page {
	static url = '/'
	
	static at = {
		title == 'Google'
	}

	static content = {
		searchBox { $('input[type="text"]', name:'q') }
		searchButton { $('button', text:contains('Google Search')) }
	}
}
```

This is using a site relative URL, with a check for the title of the page and
defining the search box and search button using Geb's jQuery-like syntax.  Note
how it supports matching on the attributes to the tag (name) and the tags text
context (text) using both exact and inexact matches (contains).

## 8. Create a simple Spock Specification

Create another new file `test/functional/MyFirstTestSpec.groovy` with:
```
import geb.spock.GebReportingSpec
import spock.lang.*

class MyFirstTestSpec extends GebReportingSpec {
	def 'test Google is up'() {
		when:
			to GoogleHomePage
		then:
			at GoogleHomePage
	}
}
```

This should just take us to the Google home page and check it's loaded (by
looking at the title, and ensuring all require content is found) as defined by
the page object.

## 9. Give it a whirl!

Test it out, with the following command:

Windows
```
> grails test-app -echoOut functional: "-baseUrl=http://www.google.co.uk"
```
Mac/Linux
```
$ grails test-app -echoOut functional: -baseUrl=http://www.google.co.uk
```

It will take a while to get everything downloaded to start with, but be
patient!

The `-baseUrl` parameter tells grails not to start up its own server (which,
for us, will do nothing anyway) but just to connect to a remote site.

The `functional:` parameter means that grails won't try to run any unit tests
either (again, we don't have any anyway).

`-echoOut` just means that any `println`s we've added to the code for
debugging tests won't get swallowed.

## 10. Check the results

The output will be in `target/test-reports`.  There is an `html` report and the
screenshots taken at the end of each test (success or failure) are under `geb`.

# Now what?

You're good to go!  Start by experimenting with adding new things to the page
object and then using them and checking them in the spec test.

This repo has a slightly more complicated example of testing the Google
calculator in `test/functional` if you need some inspiration.

I really recommend reading the documentation:
   * The Book of Geb - http://www.gebish.org/manual/0.7.2/
   * Spock Basics - http://code.google.com/p/spock/wiki/SpockBasics
