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

