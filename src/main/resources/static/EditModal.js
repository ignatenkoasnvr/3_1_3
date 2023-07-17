let formEdit = document.forms["formEditUser"];
editModal();

async function editModalData(id) {
    const modal = new bootstrap.Modal(document.querySelector('#editModal'));
    await userToModal(formEdit, modal, id);
}

function editModal() {
    formEdit.addEventListener("submit", ev => {
        ev.preventDefault();
        let editUserRoles = [];
        for (let i = 0; i < formEdit.roles.options.length; i++) {
            if (formEdit.roles.options[i].selected) editUserRoles.push({
                id: formEdit.roles.value,
                name: "ROLE_" + formEdit.roles.options[i].text
            });
        }
        fetch("http://localhost:8080/admin/users/" + formEdit.id.value, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: formEdit.id.value,
                firstName: formEdit.firstName.value,
                lastName: formEdit.lastName.value,
                password: formEdit.password.value,
                age: formEdit.age.value,
                email: formEdit.email.value,
                roles: editUserRoles
            })
        }).then(() => {
            $('#editFormCloseButton').click();
            findAll();
        });
    });
}