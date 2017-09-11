const baseurl = 'http://localhost:8080/recipes';

function createListElement(recipe) {
	$('<li></li>')
		.html(`
				<a href="#" data-recipe-id="${recipe.id}">
					${recipe.title}
				</a>
				
				<form class="delete-recipe-form" method="post" action="/recipes/${recipe.id}">
					<button>Delete</button>
				</form>
			`)
		.appendTo($('#recipe-list'));		
}

$(document).on('submit', '.delete-recipe-form', function (e) {
	e.preventDefault();
	
	$.ajax(this.action, { type: 'DELETE' })
		.done(() => {
			$(this)
				.closest('li')
				.remove();
		})
		.fail(error => console.error(error));
});

$('#create-recipe-form').on('submit', function (e) {
	e.preventDefault();
	
	let payload = {
		title: $('#title').val(),
		description: $('#description').val(),
		minutes: $('#minutes').val()
	};
	
	let ajaxOptions = {
		type: 'POST',
		data: JSON.stringify(payload),
		contentType: 'application/json'
	};
	
	$.ajax(this.action, ajaxOptions)
		.done(function (recipe) {
			createListElement(recipe)
		})
		.fail(error => console.error(error));
});

$(document).on('submit', '#create-instruction-form', function (e) {
	e.preventDefault();
	
	let payload = {
			instruction: $('#instruction').val()
	};
	
	let ajaxOptions = {
			type: 'POST',
			data: JSON.stringify(payload),
			contentType: 'application/json'
	};
	
	$.ajax(this.action, ajaxOptions)
		.done(function (instruction) {
			console.log(instruction);
		})
		.fail(error => console.error(error));
});

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
					<form id="create-instruction-form" method="post" action="/recipes/${data.id}/instructions">
						<input type="text" id="instruction" name="instruction" >
						<button>Add Instruction</button>
					</form>
			`);
	});
});
		
$.getJSON(baseurl, function (data) {
	if (data.length) {
		for (let recipe of data) {
			createListElement(recipe);		
		}
	} else {
		$('<li></li>')
			.html('You have no recipes, SON!')
			.appendTo($('#recipe-list'));
	}
});