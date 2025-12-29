const API_URL = "http://localhost:8082/api/courses";

/* Load all customers */
function loadCourses() {
    fetch(API_URL)
        .then(res => res.json())
        .then(data => {
            const table = document.getElementById("courseTable");
            table.innerHTML = "";

            data.forEach(customer => {
                table.innerHTML += `
                    <tr>
                        <td>${customer.id}</td>
                        <td>${customer.title}</td>        <!-- Customer Name -->
                        <td>${customer.description}</td>  <!-- Address -->
                        <td>${customer.duration}</td>     <!-- Phone -->
                        <td>${customer.price}</td>        <!-- Age -->
                        <td>
                            <button class="delete-btn" onclick="deleteCourse(${customer.id})">
                                Delete
                            </button>
                        </td>
                    </tr>
                `;
            });
        })
        .catch(err => console.error("Load error:", err));
}

/* Add customer (HTML IDs + backend unchanged) */
function addCourse() {

    const customer = {
        title: document.getElementById("title").value,               // Customer Name
        description: document.getElementById("description").value,   // Address
        duration: Number(document.getElementById("duration").value), // Phone
        price: Number(document.getElementById("price").value)        // Age
    };

    fetch(API_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(customer)
    })
        .then(res => {
            if (!res.ok) throw new Error("Add customer failed");
            loadCourses();
            document.querySelectorAll("input").forEach(i => i.value = "");
        })
        .catch(err => console.error("Add error:", err));
}

/* Delete customer */
function deleteCourse(id) {
    fetch(`${API_URL}/${id}`, {
        method: "DELETE"
    })
        .then(() => loadCourses())
        .catch(err => console.error("Delete error:", err));
}

/* Auto load customers on page load */
loadCourses();
