bindList($("ul.entities"));
$(".title").bind("click", {
	listItems : $("ul.entities > li")
}, function(event) {
	resetListItems(event.data.listItems);
});
$("li.users > a").bind("click", {
	listItem : $("li.users")
}, function(event) {
	populateUserList();
});

function populateUserList() {
	$
			.ajax({
				url : "http://localhost:8080/StoryTaskerServer/service/tasker/user/list",
				dataType : "json",
				success : function(data, textStatus, jqXHR) {
					displayUsers(data);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					/* TODO: Replace with better error. */ 
					alert("error:" + textStatus + " " + errorThrown);
				}
			});
}

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
 * Displays users given template and users.
 * 
 * @param users Array of users.
 */
function displayUsers(users) {
	/* TODO: Pre-compile. */
	var liTemplate = Handlebars.compile($("#user-list-template").html());
	var liHtml = liTemplate({
		users : users
	});
	$("ul.users").html(liHtml);
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
