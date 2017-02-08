<div class="row" id="images">
  <!-- Modal Trigger -->
  <a href="#addImage">Ajouter une image</a>

  <!-- Modal Structure -->
  <div id="addImage" class="modal">
    <div class="card input-card horizontal">
      <div class="card-image">
        <img id="image"/>
      </div>
      <div class="card-stacked">
        <div class="card-content">
          <span class="card-title">Chercher une image</span>
          <div class="file-field input-field">
            <div class="btn"><span>Image</span>
              <input id="file-input" type="file" onchange="onImageChange(this)" />
            </div>
            <div class="file-path-wrapper">
              <input class="file-path validate" type="text" />
            </div>
          </div>
        </div>
        <div class="card-action">
          <a class="waves-effect waves-teal right btn-flat"
            onclick="onCardValid(
            document.getElementById('file-input'))">Valider</a>
        </div>
      </div>
    </div>
    <div id="images-input"></div>
  <script>
    var container = document.getElementById("images")
    var containerInputs = document.getElementById("images-input");
    var imageViewer = document.getElementById("image-viewer");
    var onImageChange = function (input) {
      var reader = new FileReader();
      reader.onload = function (e) {
          document.getElementById("image").src = e.target.result;
      };
      reader.readAsDataURL(input.files[0]);
    };
    function onCardValid(input) {
      createImage(input)
    }
    function createImage(input) {
      var cloneInput = input.cloneNode(true);
      cloneInput.name = "images[]"
      cloneInput.className = "hide";
      containerInputs.appendChild(cloneInput);

      var div = document.createElement("div");
      div.className = "col s3";
      var image = document.createElement("img");
      image.src = document.getElementById("image").src;
      image.style.width = "100%";
      div.appendChild(image);
      container.appendChild(div);
    }
  </script>
</div>
