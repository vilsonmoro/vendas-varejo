document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.tabs');
    M.Tabs.init(elems);

    const selects = document.querySelectorAll('select');
    M.FormSelect.init(selects);
});

function confirmLogout(event) {
    event.preventDefault();
    const confirmed = confirm("Você deseja realmente sair da aplicação?");
    if (confirmed) {
        window.location.href = "login.html";
    }
}

document.getElementById('cadastrarBtn').addEventListener('click', async function() {
    const confirmed = confirm("Você deseja realmente cadastrar?");
    if (!confirmed) {
        this.style.backgroundColor = '#FFE100'; 
        return;
    }

    this.style.backgroundColor = '#FFE100';

    const token = localStorage.getItem('token'); // Recupera o token do localStorage
    const clienteData = {
        nome: document.getElementById('nome').value,
        formaPagamento: document.getElementById('formapagamento').value, // Agora estamos pegando o valor do select
        tipoEntrega: document.getElementById('tipo_entrega').value,
        observacoes: document.getElementById('observacoes').value
    };

    const enderecoData = {
        logadouro: document.getElementById('logadouro').value,
        cidade: document.getElementById('cidade').value,
        estado: document.getElementById('estado').value,
        cep: document.getElementById('cep').value
    };

    try {
        // Cadastrar Endereço primeiro
        const enderecoResponse = await fetch('http://localhost:8080/endereco', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(enderecoData)
        });

        if (!enderecoResponse.ok) {
            throw new Error('Erro ao cadastrar endereço');
        }

        const endereco = await enderecoResponse.json();
        console.log('Endereço cadastrado:', endereco);

        // Cadastrar Cliente, apenas se o endereço for cadastrado com sucesso
        const clienteResponse = await fetch('http://localhost:8080/cliente', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(clienteData)
        });

        if (!clienteResponse.ok) {
            throw new Error('Erro ao cadastrar cliente');
        }

        const cliente = await clienteResponse.json();
        console.log('Cliente cadastrado:', cliente);

        alert('Cadastro realizado com sucesso!');
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao realizar cadastro. Verifique os dados e tente novamente.');
    }
});
