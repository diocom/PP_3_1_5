$(async function() {
    await showUser();
});
async function showUser() {
    fetch("http://localhost:8088/api/user")
        .then(res => res.json())
        .then(data => {
            $('#headUserName').append(data.userName);
            let roles = data.roles.map(e => " " + e.name);
            $('#headRoles').append(roles);

            let user = `$(
            <tr>
                <td>${data.id}</td>
                <td>${data.name}</td>
                <td>${data.lastName}</td>
                <td>${data.age}</td>
                <td>${data.userName}</td>
                <td>${roles}</td>)`;
            $('#userData').append(user);
        })
}