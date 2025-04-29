document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('.formLogin');
    form.addEventListener('submit', function (event) {
        event.preventDefault();
    
        const login = document.getElementById('login').value; 
        const password = document.getElementById('password').value;
    
        if (!login || login.trim().length < 3) {
            alert('Por favor, insira um email válido ou um nome de usuário (mínimo 3 caracteres).');
            return;
        }
    
        if (!password) {
            alert('Por favor, insira sua senha.');
            return;
        }
    
        // Montar o objeto de dados para enviar
        const data = {
            usuario: login,
            senha: password
        };
    
        // Chamada à API para autenticação
        fetch('/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro na autenticação');
            }
            return response.json();
        })
        .then(data => {
            // Verifique se o token foi retornado na resposta
            if (data.token) {
                // Salvar o token no localStorage
                localStorage.setItem('token', data.token);
                
                // Decodificar o token JWT para extrair o ID do usuário
                const decodedToken = JSON.parse(atob(data.token.split('.')[1]));
                const userId = decodedToken.userId; // O ID do usuário está na claim "userId"

                // Salvar o userId no localStorage
                localStorage.setItem('userId', userId);

                // Redirecionar para a página inicial
                window.location.href = "../paginainicial"; // ou qualquer página desejada
            } else {
                alert('Erro: Token não recebido.');
            }
        })
        .catch(error => {
            console.error('Erro ao se conectar ao servidor:', error);
            alert('Erro ao se conectar ao servidor: ' + error.message);
        });
    });
});
