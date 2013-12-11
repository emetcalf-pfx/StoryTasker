/**
 * Binds listItems to hide/show on click.
 * 
 * @param list
 *            jQuery object of list element.
 */
function bindList(list) {
	if (list == undefined) {
		return;
	} else {
		var listItems = list.children("li");
		listItems.each(function() {
			$(this).children("a").bind("click", {
				listItems : listItems,
				selectedItem : this
			}, toggleActive);
			bindList($(this).children("ul"));
		});
	}
}

/**
 * Toggles list item between active and not along with hiding/showing it's
 * sub-list elements.
 * 
 * @param event
 *            Include selectedItem and listItems in data.
 */
function toggleActive(event) {
	if ($(event.data.selectedItem).hasClass("active")) {
		var childListItems = $(event.data.selectedItem).children("ul")
				.children("li");
		resetListItems(childListItems);
	} else {
		makeItemActive(event.data.selectedItem);
		hideAllItemsButI(event.data.listItems, event.data.selectedItem.id);
	}
}

/**
 * Resets given list items to not be visible.
 * 
 * @param listItems
 */
function resetListItems(listItems) {
	listItems.removeClass("active");
	listItems.children("ul").fadeOut();
	listItems.fadeIn();
}

/**
 * Activates list item so visible.
 * 
 * @param listItem
 */
function makeItemActive(listItem) {
	$(listItem).addClass("active");
	$(listItem).children("ul").fadeIn();
}

/**
 * Hides all items except item with given selected item id.
 * 
 * @param listItems
 * @param selectedItemId
 */
function hideAllItemsButI(listItems, selectedItemId) {
	$(listItems).each(function() {
		if (this.id != selectedItemId) {
			$(this).fadeOut();
		}
	});
}
