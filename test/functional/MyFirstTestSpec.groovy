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

