async function userToModal(form, modal, id) {
    modal.show();
    let user = await findUser(id);
    form.id.value = user.id;
    form.firstName.value = user.firstName;
    form.lastName.value = user.lastName;
    form.password.value = user.password;
    form.age.value = user.age;
    form.email.value = user.email;
    form.roles.value = user.roles[0].id;
}