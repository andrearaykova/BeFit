var objJson = null;

$(function () {
    $("#error").css("display", "none");

    let lastSlashIndex = window.location.pathname.lastIndexOf("/");
    let exerciseId = window.location.pathname.substring(lastSlashIndex + 1);

    loadComments();

    $("#form").submit(function (e) {
        e.preventDefault();

        let comment = $("#comment").val();

        if (comment === "" || comment.length > 255) {
            $("#error").css("display", "block");
            return false;
        }

        $.ajax({
            url: "http://localhost:8000/comment/" + exerciseId,
            data: {
                comment,
            },
            method: "POST"
        })
            .done(function (data) {
                $("#error").css("display", "none");
                $("#comment").val("");
                loadComments();
            })
            .fail(function (data) {
                $("#error").css("display", "block");
            })
    });

    function loadComments() {
        $.ajax({
            url: "http://localhost:8000/comment/" + exerciseId,
            method: "GET"
        })
            .done(function (data) {
                objJson = JSON.parse(data);
                changePage(1);
            });
    }

    var current_page = 1;
    var records_per_page = 2;

    function prevPage() {
        if (current_page > 1) {
            current_page--;
            changePage(current_page);
        }
    }

    function nextPage() {
        if (current_page < numPages()) {
            current_page++;
            changePage(current_page);
        }
    }

    function changePage(page) {
        var btn_next = document.getElementById("btn_next");
        var btn_prev = document.getElementById("btn_prev");
        var listing_table = document.getElementById("listingTable");
        var page_span = document.getElementById("page");

        // Validate page
        if (page < 1) page = 1;
        if (page > numPages()) page = numPages();

        listing_table.innerHTML = "";

        for (var i = (page - 1) * records_per_page; i < (page * records_per_page); i++) {
            if (objJson[i] !== undefined) {
                listing_table.innerHTML += "<p>" + objJson[i].comment + "</p>";
            }
        }
        page_span.innerHTML = page;

        if (page === 1) {
            btn_prev.style.visibility = "hidden";
        } else {
            btn_prev.style.visibility = "visible";
        }

        if (page === numPages()) {
            btn_next.style.visibility = "hidden";
        } else {
            btn_next.style.visibility = "visible";
        }
    }

    function numPages() {
        return Math.ceil(objJson.length / records_per_page);
    }
})