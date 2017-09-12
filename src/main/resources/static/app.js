const baseurl = 'http://localhost:8080/recipes';

function fillInDetails(data) {
	let html = `
		<h1>${data.title}</h1>
		<h2>${data.description}</h2>
		<br>
		<h3>Ingredients</h3>
	`;
		
	for (let ingredient of data.ingredients) {
		html += `
			<div>
				<b>${ingredient.quantity}</b><b>${ingredient.unit}</b><b>${ingredient.name}</b>
				<form class="delete-ingredient-form" method="post" action="/recipes/${data.id}/ingredients/${ingredient.id}">
					<button>Delete Ingredient</button>
				</form>
			</div>
		`;
	}
	
	html += `
		<form id="create-ingredient-form" method="post" action="/recipes/${data.id}/ingredients">
			<input type="number" id="quantity" name="quantity" placeholder="Quantity">
			<br>
			<input type="text" id="unit" name="unit" placeholder="Unit">
			<br>
			<input type="text" id="name" name="name" placeholder="Name">
			<button>Add Ingredient</button>
		</form>
		<br>
		<h3>Instructions</h3>
	`;
	
	for (let instruction of data.instructions) {
		html += `				
			<div>
				<b>${instruction.instruction}</b>
				<form class="delete-instruction-form" method="post" action="/recipes/${data.id}/instructions/${instruction.id}">
					<button>Delete Instruction</button>
				</form>
			</div>
		`;
	}
	
	html += `
			<br>
			<form id="create-instruction-form" method="post" action="/recipes/${data.id}/instructions">
				<input type="text" id="instruction" name="instruction" placeholder="Instruction">
				<button>Add Instruction</button>
			</form>
		`;
	
	$('#recipe-detail').html(html);	
}

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

$(document).on('submit', '.delete-ingredient-form', function (e) {
	e.preventDefault();
	
	$.ajax(this.action, { type: 'DELETE' })
		.done(() => {
			$(this)
				.closest('div')
				.remove();
		})
		.fail(error => console.error(error));
});

$(document).on('submit', '.delete-instruction-form', function (e) {
	e.preventDefault();
	
	$.ajax(this.action, { type: 'DELETE' })
		.done(() => {
			$(this)
				.closest('div')
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
			fillInDetails(instruction);
		})
		.fail(error => console.error(error));
});

$(document).on('submit', '#create-ingredient-form', function (e) {
	e.preventDefault();
	
	let payload = {
			name: $('#name').val(),
			unit: $('#unit').val(),
			quantity: $('#quantity').val()
	};
	
	let ajaxOptions = {
			type: 'POST',
			data: JSON.stringify(payload),
			contentType: 'application/json'
	};
	
	$.ajax(this.action, ajaxOptions)
		.done(function (ingredient) {
			fillInDetails(ingredient);
		})
		.fail(error => console.error(error));
});

$(document).on('click', 'a[data-recipe-id]', function (e) {
	e.preventDefault();
	
	const recipeId = $(this).data('recipeId');
	
	$.getJSON(baseurl + '/' + recipeId, function (data) {
		data.title = data.title || '';
		data.description = data.description || '';
		
		fillInDetails(data);
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