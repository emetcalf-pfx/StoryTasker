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


