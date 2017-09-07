const baseurl = 'http://localhost:8080/recipes';

$(document).on('click', 'a[data-recipe-id]', function (e) {
	e.preventDefault();
	const recipeId = $(this).data('recipeId');
	$.getJSON(baseurl + '/' + recipeId, function (data) {
		data.title = data.title || '';
		data.description = data.description || '';
		$('#recipe-detail')
			.html(`
					<h1>${data.title}</h1>
					<h2>${data.description}</h2>
					
			`);
	});
});
		
$.getJSON(baseurl, function (data) {
	if (data.length) {
		for (let recipe of data) {
			$('<li></li>')
			.html('<a href="#" data-recipe-id="' + recipe.id + '">' + recipe.title + '</a>')
			.appendTo($('#recipe-list'));			
		}
	} else {
		$('<li></li>')
			.html('You have no recipes, SON!')
			.appendTo($('#recipe-list'));
	}
});