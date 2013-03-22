import geb.Page

class GoogleHomePage extends Page {

	static url = '/'
	
	static at = {
		title == 'Google'
	}

	static content = {
		searchBox { $('input[type="text"]', name:'q') }
		searchButton(required:false) { $('button', text:contains('Google Search')) }
		
		oneButton(required:false) { $('div.cwbd span', text:'1') }
		plusButton(required:false) { $('div.cwbd span', text:'+') }
		equalsButton(required:false) { $('div.cwbd span', text:'=') }
		totalBox(required:false) { $('span.cwcot') }
	}
}

