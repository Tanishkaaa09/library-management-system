const API_URL = "http://localhost:8081/api/books";

/* Load all customers */
function loadBooks() {
    fetch(API_URL)
        .then(res => res.json())
        .then(data => {
            const table = document.getElementById("bookTable");
            table.innerHTML = "";

            data.forEach(customer => {
                table.innerHTML += `
                    <tr>
                        <td>${customer.id}</td>
                        <td>${customer.title}</td>     <!-- Name -->
                        <td>${customer.author}</td>    <!-- Email / Phone -->
                        <td>${customer.copies}</td>    <!-- Age / Count -->
                        <td>
                            <button class="delete-btn" onclick="deleteBook(${customer.id})">
                                Delete
                            </button>
                        </td>
                    </tr>
                `;
            });
        })
        .catch(err => console.error("Load error:", err));
}

/* Add customer (tumhara code – unchanged) */
function addBook() {

    const title = document.getElementById("title").value;
    const author = document.getElementById("author").value;
    const copies = document.getElementById("copies").value;

    const book = {
        title: title,
        author: author,
        copies: Number(copies)
    };

    fetch(API_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(book)
    })
    .then(res => {
        if (!res.ok) {
            throw new Error("Add failed");
        }
        loadBooks();
        document.querySelectorAll("input").forEach(i => i.value = "");
    })
    .catch(err => console.error("Error:", err));
}

/* Delete customer */
function deleteBook(id) {
    fetch(`${API_URL}/${id}`, {
        method: "DELETE"
    })
    .then(() => loadBooks())
    .catch(err => console.error("Delete error:", err));
}

/* Auto load when page opens */
loadBooks();
