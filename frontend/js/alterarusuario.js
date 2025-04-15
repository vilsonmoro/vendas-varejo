// Função para carregar as informações do usuário a partir da API
async function loadUserDataForEdit() {
    const token = localStorage.getItem('token'); // Token para autenticação, se necessário
    const id = localStorage.getItem('idusuario'); // Pega o ID do usuário armazenado no localStorage

    if (!id) {
        alert('ID do usuário não encontrado no localStorage.');
        return;
    }

    // Faz a requisição para a API para obter os dados do usuário
    try {
        const response = await fetch(`http://localhost:8080/usuario/${id}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error('Erro ao buscar usuário: ' + response.statusText);
        }

        const user = await response.json();

        // Preencher os campos do formulário com os dados do usuário
        document.getElementById('nome').value = user.nome;
        document.getElementById('sobrenome').value = user.sobrenome;
        document.getElementById('usuario').value = user.usuario;
        document.getElementById('email').value = user.email;

    } catch (error) {
        alert('Erro ao buscar dados do usuário: ' + error.message);
    }
}

// Função para remover o idusuario do localStorage
function removeUserFromLocalStorage() {
    localStorage.removeItem('idusuario');
}

// Função para enviar os dados atualizados para o backend
async function saveUserData(event) {
    const token = localStorage.getItem('token');
    const id = localStorage.getItem('idusuario');

    if (!id) {
        alert('ID do usuário não encontrado no localStorage.');
        return;
    }
	const nome = document.getElementById('nome').value;
	const sobrenome = document.getElementById('sobrenome').value;
    const usuario = document.getElementById('usuario').value;
    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;

    // Validação do nome de usuário
    if (usuario.length < 3 || usuario.length > 25) {
        alert('O usuário deve ter entre 3 e 25 caracteres.');
        event.preventDefault();
        return;
    }

    if (usuario.includes(' ')) {
        alert('O usuário não pode conter espaços.');
        event.preventDefault();
        return;
    }

    // Validação do e-mail
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(email)) {
        alert('Por favor, insira um e-mail válido.');
        event.preventDefault();
        return;
    }

    // Validação da senha
    const senhaPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^a-zA-Z0-9]).{8,}$/;
    if (senha && !senhaPattern.test(senha)) {
        alert('A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um caractere especial e ter no mínimo 8 caracteres.');
        event.preventDefault();
        return;
    }

    // Verificação se as senhas coincidem
    if (senha !== confirmarSenha) {
        alert('As senhas não coincidem.');
        event.preventDefault();
        return;
    }

    // Dados a serem enviados para a API
    const updatedUser = {
		id,
		nome,
		sobrenome,
        usuario,
        email,
        senha // Envia a senha somente se ela foi alterada
    };

    try {
        const response = await fetch(`http://localhost:8080/usuario/${id}`, {
            method: 'PUT',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedUser)
        });

        if (!response.ok) {
            throw new Error('Erro ao salvar as alterações: ' + response.statusText);
        }

        // Remover o idusuario do localStorage após salvar
        removeUserFromLocalStorage();

        // Informar ao usuário que a operação foi bem-sucedida
        alert('Usuário atualizado com sucesso!');
    } catch (error) {
        alert('Erro ao salvar as alterações: ' + error.message);
    }
}

// Carregar as informações do usuário quando a página for carregada
window.addEventListener('load', function () {
    loadUserDataForEdit();
});

// Ajuste do evento do botão "Salvar"
document.querySelector('.btn').addEventListener('click', saveUserData);

// Função para confirmar o logout e excluir o idusuario do localStorage
function confirmLogout(event) {
    event.preventDefault();
    const confirmed = confirm("Você deseja realmente sair da aplicação?");
    if (confirmed) {
        removeUserFromLocalStorage();  // Remover o idusuario ao sair
        window.location.href = "login.html";
    }
}

// Alterar visibilidade da senha
function togglePasswordVisibility(inputId) {
    const input = document.getElementById(inputId);
    const eyeIcon = input.nextElementSibling.querySelector('.material-icons');

    if (input.type === 'password') {
        input.type = 'text';
        eyeIcon.textContent = 'visibility';
    } else {
        input.type = 'password';
        eyeIcon.textContent = 'visibility_off';
    }
}
