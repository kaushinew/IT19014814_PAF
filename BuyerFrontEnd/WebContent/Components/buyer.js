/**
 * 
 */
$(document).ready(function()
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateItemForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
		{
			url: "BuyerAPI",
			type: type,
			data: $("#formItem").serialize(),
			dataType: "text",
			complete: function(response, status) {
				onItemSaveComplete(response.responseText, status);
			}
		});
}); 
function onItemSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
 $("#hidItemIDSave").val(""); 
 $("#formItem")[0].reset(); 
}
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
 $("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val()); 
 $("#userID").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#productID").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#quantity").val($(this).closest("tr").find('td:eq(2)').text()); 
 
}); 

$(document).on("click", ".btnRemove", function(event)
{ 
 $.ajax( 
 { 
 url : "BuyerAPI", 
 type : "DELETE", 
 data : "purchaseID=" + $(this).data("purchaseid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemDeleteComplete(response.responseText, status); 
 } 
 }); 
});
function onItemDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}
// CLIENT-MODEL================================================================
function validateItemForm() 
{ 
// CODE
if ($("#userID").val().trim() == "") 
 { 
 return "Insert User ID."; 
 } 
// NAME
if ($("#productID").val().trim() == "") 
 { 
 return "Insert Product ID."; 
 } 9
// PRICE-------------------------------
if ($("#quantity").val().trim() == "") 
 { 
 return "Insert Quantity."; 
 } 
// is numerical value
var tmpQ = $("#quantity").val().trim(); 
if (!$.isNumeric(tmpQ)) 
 { 
 return "Insert a numerical value for Quantity."; 
 } 
/*// convert to decimal price
 $("#quantity").val(parseFloat(tmpPrice).toFixed(2));*/ 

return true; 
}