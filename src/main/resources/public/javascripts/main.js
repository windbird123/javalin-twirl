document.addEventListener("htmx:afterRequest", function (event) {
  const target = event.target;

  if (target.matches("form[data-update-url]")) {
    const url = target.getAttribute("action") || target.getAttribute("hx-get") || target.getAttribute("hx-post");
    const params = new URLSearchParams(new FormData(target)).toString();
    window.history.pushState({}, "", `${url}?${params}`);
  }
});
