function populateUserList() {
	$
			.ajax({
				url: "http://localhost:8080/StoryTaskerServer/service/tasker/users",
				dataType : "json",
				success : function(data, textStatus, jqXHR) {
					displayUsers(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					/* TODO: Replace with better error. */ 
					alert("error: " + textStatus + " " + errorThrown);
				}
			});
}

function createUser() {
	$.ajax({
		url : "http://localhost:8080/StoryTaskerServer/service/tasker/users",
		contentType: "application/json",
		dataType : "json",
		data: JSON.stringify({username: $("#username").val()}),
		type: "POST",
		success : function(data) {
			populateUserList();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			/* TODO: Replace with better UI error. */
			alert($.parseJSON(jqXHR.responseText).message);
			$("#username").select();
		}
	});
}

function displayAddUserForm() {
	$("#user-add-button").hide();
	$("#user-add-form").show();
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
	$("#user-add-button").bind("click", function() {
		displayAddUserForm();
	});
	$("#user-add-form").bind("submit", function() {
		createUser();
		return false;
	});
}
