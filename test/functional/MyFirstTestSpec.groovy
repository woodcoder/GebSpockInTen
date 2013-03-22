import geb.spock.GebReportingSpec
import spock.lang.*

class MyFirstTestSpec extends GebReportingSpec {
	def 'test Google calculator'() {
		when:
			to GoogleHomePage
		then:
			at GoogleHomePage
		
		when: 'bring up the calculator'
			searchBox = 'calculator'
		then: 'search button is hidden'
			!searchButton.present
		and: 'the calculator appears'
			waitFor { equalsButton.present }
			
		when: '1 + 1 ='
			oneButton.click()
			plusButton.click()
			oneButton.click()
			equalsButton.click()
		then:
			totalBox.text() == '2'
	}
}
