window.onload = function() {
	//Check File API support
	if(!(window.File && window.FileList && window.FileReader)) {
		console.log("Your browser does not support File API"); return;
	}

	var filesInput = document.getElementsByClassName("files-input");
	for (var i = 0; i < filesInput.length; i++) {

		filesInput[i].addEventListener("change", function(e) {
			var files = e.target.files; //FileList object
			var container = e.target.parentElement.getElementsByClassName("multiple-input-images")[0];
			container.innerHTML = "";
			for(var i = 0; i < files.length; i++) {
				var file = files[i];

				//Only pics
				if(!file.type.match('image.*')) continue;
				var picReader = new FileReader();

				picReader.addEventListener("load", function(e) {
					//add a loaded pic
					var picFile = e.target;
					var div = document.createElement("div");
					div.className = "col s3";
					div.innerHTML = "<img src='" + picFile.result + "'" +
						"title='" + picFile.name + "' style='width: 100%;'/>";
					container.appendChild(div);
				});

				 //Read the image
				picReader.readAsDataURL(file);
			}
		});
	}
}
