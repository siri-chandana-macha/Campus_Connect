/**
 * Show/hide the upload form
 * forceClose = true will always close
 */
function toggleForm(forceClose = false) {
  const form = document.getElementById("uploadForm");
  const overlay = document.getElementById("overlay");
  const isOpen = form.style.display === "flex";

  if (forceClose || isOpen) {
    form.style.display = "none";
    overlay.style.display = "none";
  } else {
    form.style.display = "flex";
    overlay.style.display = "block";
  }
}

/** Handle upload, create card, reset/hide form */
function uploadPodcast() {
  const title = document.getElementById("title").value.trim();
  const description = document.getElementById("description").value.trim();
  const fileInput = document.getElementById("file");
  const file = fileInput.files[0];

  if (!title || !description || !file) {
    alert("All fields are required.");
    return;
  }

  // Determine preview
  const fileURL = URL.createObjectURL(file);
  let mediaPreview = "";
  if (file.type.startsWith("video")) {
    mediaPreview = `<video src="${fileURL}" controls style="width:100%;border-radius:10px;"></video>`;
  } else if (file.type.startsWith("audio")) {
    mediaPreview = `<audio src="${fileURL}" controls style="width:100%;"></audio>`;
  } else {
    alert("Unsupported file type.");
    return;
  }

  // Build card
  const card = document.createElement("div");
  card.className = "podcast-card";
  card.innerHTML = `
    <div class="menu" onclick="toggleDropdown(this)">⋮</div>
    <div class="dropdown">
      <button onclick="deleteCard(this)">Delete</button>
    </div>
    ${mediaPreview}
    <h3>${title}</h3>
    <p>${description}</p>
  `;

  // Card click → preview modal
  card.addEventListener("click", function(e) {
    if (e.target.classList.contains("menu") || e.target.tagName === "BUTTON") return;
    document.getElementById("modalTitle").textContent = title;
    document.getElementById("modalDescription").textContent = description;
    document.getElementById("mediaContainer").innerHTML = mediaPreview;
    document.getElementById("previewModal").style.display = "block";
  });

  // Insert and hide empty state
  document.getElementById("podcastList").appendChild(card);
  document.getElementById("noPodcasts").style.display = "none";

  // Reset + close form
  document.getElementById("uploadForm").reset();
  toggleForm(true);
}

/** Show/hide the three-dots menu */
function toggleDropdown(el) {
  const dd = el.nextElementSibling;
  dd.style.display = dd.style.display === "block" ? "none" : "block";
}

/** Remove a podcast card */
function deleteCard(btn) {
  const card = btn.closest(".podcast-card");
  card.remove();
  if (!document.getElementById("podcastList").firstElementChild) {
    document.getElementById("noPodcasts").style.display = "block";
  }
}

/** Close the preview modal */
function closeModal() {
  document.getElementById("previewModal").style.display = "none";
}
