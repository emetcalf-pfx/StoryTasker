bindListItems("ul.entities > li");
bindListItems("ul.stories > li");
$(".title").bind("click", {
	listItems : $("ul.entities > li")
}, function(event) {
	resetListItems(event.data.listItems)
});

function bindListItems(listItems) {
	$(listItems).each(function() {
		$(this).children("a").bind("click", {
			listItems : listItems,
			selectedItem : this
		}, toggleActive);
	});
}

function toggleActive(event) {
	if ($(event.data.selectedItem).hasClass("active")) {
		var childListItems = $(event.data.selectedItem).children("ul")
				.children("li");
		resetListItems(childListItems);
	} else {
		makeItemActive(event.data.selectedItem);
		hideAllItemsButI(event.data.listItems, event.data.selectedItem);
	}
}

function resetListItems(listItems) {
	listItems.removeClass("active");
	listItems.children("ul").fadeOut();
	listItems.fadeIn();
}

function makeItemActive(listItem) {
	$(listItem).addClass("active");
	$(listItem).children("ul").fadeIn();
}

function hideAllItemsButI(listItems, selectedItem) {
	$(listItems).each(function() {
		if (this.id != selectedItem.id) {
			$(this).fadeOut();
		}
	});
}
