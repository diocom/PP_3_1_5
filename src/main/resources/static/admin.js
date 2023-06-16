const userLogin = document.getElementById('userLogin')

$(async function () {
     await showAdmin();
     await listRoles();
     userSelectRole();
    }
);
async function showAdmin() {
    await fetch("http://localhost:8088/api/admin/admin")
        .then(res => res.json())
        .then(data => {
            let userTd = ''
            let headRoles = ''
            data.forEach(userFromRest => {
                if (userFromRest.userName === userLogin.innerText)
                    headRoles += `${userFromRest.roles.map(m => m.name)}`

                userTd += `<tr>
                <td id = "user${userFromRest.id}">${userFromRest.id}</td>
                <td>${userFromRest.name}</td>
                <td>${userFromRest.lastName}</td>
                <td>${userFromRest.age}</td>
                <td>${userFromRest.userName}</td>
                <td>${userFromRest.roles.map(m => m.name)}</td>
                <td>
                    <button type="button" 
                            class="btn btn-info btn-sm"
                            id="editBtn"
                            data-toggle="modal"
                            data-index="${userFromRest.id}"
                            data-target="#modalEdit">Edit
                    </button>
                </td>
                <td>
                    <button type="button"
                            id="deleteBtn" 
                            class="btn btn-danger btn-sm"
                            data-index="${userFromRest.id}"
                            data-toggle="modal"
                            data-target="#modalDel">Delete
                    </button>
                </td>
                <td>
                `
            })
            document.getElementById('usersData').innerHTML = userTd
            document.getElementById('rolesLogin').innerHTML = headRoles

        })
}

async function listRoles() {
    await fetch("http://localhost:8088/api/admin/roles")
        .then(response => response.json())
        .then(data => {
            let lsRoles = ''
            data.forEach(element => {
                if (element.id === 2) {
                    lsRoles +=
                        `
                    <option value='${element.id}'>
                    ${element.name}
                    </option>
                    `
                } else if (element.id === 1) {
                    lsRoles +=
                        `
                    <option value='${element.id}'>
                    ${element.name}
                    </option>
                    `
                }
            })
            roles.innerHTML = lsRoles
        })
}

$('.nav-tabs a[href="#NewUser"]').on('show.bs.tab', () => {
    listRoles()
    document.getElementById('addUserBtn').addEventListener('click', newUser)
})

function newUser(e) {
    e.preventDefault()
    let nameF = document.getElementById('name').value;
    let lastNameF = document.getElementById('lastName').value;
    let ageF = document.getElementById('age').value;
    let userNameF = document.getElementById('userName').value;
    let passwordF = document.getElementById('password').value;
    let userRoles = []
    for (let option of document.getElementById('roles').options) {
        if (option.selected) {
            if (option.innerText === 'ADMIN') {
                userRoles.push({'id': 1, 'name': 'ADMIN'})
            }
            if (option.innerText === 'USER') {
                userRoles.push({'id': 2, 'name': 'USER'})
            }
        }
    }
    fetch("http://localhost:8088/api/admin", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8',
            'Accept': 'application/json'
        },
        body: JSON.stringify({
            name: nameF,
            lastName: lastNameF,
            age: ageF,
            userName: userNameF,
            password: passwordF,
            roles: userRoles
        })
    }).then((response) => {
            document.forms['addUserForm'].reset()
            showAdmin()
            $('.nav-tabs a[href="#UsersTable"]').tab('show')
        }
    )
}


$('#modalDel').off().on('show.bs.modal', event => {
    let id = $(event.relatedTarget).attr("data-index")
    delUserView(id)
    document.getElementById('deleteUserBtn').addEventListener('click', (event) => {
        deleteUser(id)
    })

})
$('#modalEdit').off().on('show.bs.modal', event => {
    let id = $(event.relatedTarget).attr("data-index")
    patchUserView(id)
    document.getElementById('patchUserBtn').addEventListener('click', (event) => {
        patchUser(id)
    })
})

function patchUserView(id) {
    let form = document.forms['patchForm']
    fetch('http://localhost:8088/api/admin/view/' + id)
        .then(response => response.json())
        .then(rest => {
            form.id.value = rest.id
            form.name.value = rest.name
            form.lastName.value = rest.lastName
            form.age.value = rest.age
            form.userName.value = rest.userName
            form.password.value = rest.password
            userSelectRole(rest.roles, 'rolesPatch')
        })
}

function patchUser(id) {
    let idU = document.getElementById('idPatch').value;
    let nameU = document.getElementById('namePatch').value;
    let lastNameU = document.getElementById('lastNamePatch').value;
    let ageU = document.getElementById('agePatch').value;
    let userNameU = document.getElementById('userNamePatch').value;
    let passwordU = document.getElementById('passwordPatch').value;
    let patchRoles = []
    for (let selector of document.getElementById('rolesPatch').options) {
        if (selector.selected) {
            patchRoles.push({
                id: selector.value,
                name: selector.innerText
            })
        }
    }
    fetch('http://localhost:8088/api/admin/patch/' + id, {
        method: 'PATCH', headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8',
            'Referer': null
        },
        body: JSON.stringify({
            id: idU,
            name: nameU,
            lastName: lastNameU,
            userName: userNameU,
            age: ageU,
            password: passwordU,
            roles: patchRoles
        })
    }).then(() => {
        showAdmin()
        document.getElementById('closeViewPatch').click()
        $('.nav-tabs a[href="#UsersTable"]').tab('show')
    })
}

function deleteUser(id) {
    fetch('http://localhost:8088/api/admin/delete/' + id, {
        method: 'DELETE', headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8',
            'Referer': null
        }
    }).then(() => {
        showAdmin()
        document.getElementById('closeViewDel').click()
        $('.nav-tabs a[href="#UsersTable"]').tab('show')
    })
}

function delUserView(id) {
    let form = document.forms['deleteForm']
    fetch('http://localhost:8088/api/admin/view/' + id)
        .then(response => response.json())
        .then(rest => {
            form.id.value = rest.id
            form.name.value = rest.name
            form.lastName.value = rest.lastName
            form.userName.value = rest.userName
            form.age.value = rest.age
            form.password.value = rest.password
            userSelectRole(rest.roles, 'rolesDelete')
        })
}

function userSelectRole(roles, elemId) {

    fetch('http://localhost:8088/api/admin/roles')
        .then(response => response.json())
        .then(rest => {
            let rolesToForm = document.getElementById(elemId)
            let rowElement = ''
            rest.forEach(r => {
                let roleCurrent = roles.map(roles => roles.name)
                if (roleCurrent.includes(r.name)) {
                    rowElement += `<option value='${r.id}' selected>
                                   ${r.name}</option>`
                } else if (!roleCurrent.includes(r.name)) {
                    rowElement += `<option value='${r.id}'>
                                   ${r.name}</option>`
                }
            })
            rolesToForm.innerHTML = rowElement
        })
}
