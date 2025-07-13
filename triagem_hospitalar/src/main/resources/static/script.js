document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("paciente-form");
  const lista = document.getElementById("pacientes-lista");

  let pacienteEditando = null;

  // Função para carregar a lista de pacientes
  function carregarPacientes() {
    fetch("http://localhost:8080/api/pacientes")  // Endpoint do backend
      .then(res => res.json())
      .then(pacientes => {
        lista.innerHTML = "";
        pacientes.forEach(paciente => {
          const card = document.createElement("div");
          card.classList.add("card");

          const corClasse = {
            "Vermelho": "vermelho",
            "Laranja": "laranja",
            "Amarelo": "amarelo",
            "Verde": "verde",
            "Azul": "azul"
          };

          card.innerHTML = `
            <h3>${paciente.nome}</h3>
            <p><strong>Idade:</strong> ${paciente.idade}</p>
            <p><strong>Responsável:</strong> ${paciente.nomeResponsavel || "Não informado"}</p>
            <p><strong>Telefone:</strong> ${paciente.telefone}</p>
            <p><strong>Endereço:</strong> ${paciente.endereco}</p>
            <p><strong>Classificação:</strong> 
              <span class="${corClasse[paciente.classificacaoRisco] || ''}">
                ${paciente.classificacaoRisco}
              </span>
            </p>
            <button onclick="editarPaciente(${paciente.id})">Editar</button>
            <button onclick="excluirPaciente(${paciente.id})">Excluir</button>
          `;
          lista.appendChild(card);
        });
      })
      .catch(err => console.error("Erro ao carregar pacientes: ", err));
  }

  // Evento de submit do formulário para adicionar ou editar paciente
  form.addEventListener("submit", function (e) {
    e.preventDefault();

    const paciente = {
      nome: document.getElementById("nome").value,
      idade: document.getElementById("idade").value,
      nomeResponsavel: document.getElementById("responsavel").value,
      telefone: document.getElementById("telefone").value,
      endereco: document.getElementById("endereco").value,
      classificacaoRisco: document.getElementById("classificacao").value
    };

    const metodo = pacienteEditando ? "PUT" : "POST";
    const url = pacienteEditando ? `http://localhost:8080/api/pacientes/${pacienteEditando}` : "http://localhost:8080/api/pacientes";

    fetch(url, {
      method: metodo,
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(paciente)
    })
      .then(res => {
        if (res.ok) {
          form.reset();
          pacienteEditando = null;
          carregarPacientes();
        } else {
          alert("Erro ao salvar paciente.");
        }
      })
      .catch(err => alert("Erro ao comunicar com o servidor."));
  });

  // Função para excluir um paciente
  window.excluirPaciente = function (id) {
    if (confirm("Deseja excluir este paciente?")) {
      fetch(`http://localhost:8080/api/pacientes/${id}`, { method: "DELETE" })
        .then(() => carregarPacientes())
        .catch(err => alert("Erro ao excluir paciente."));
    }
  };

  // Função para editar um paciente
  window.editarPaciente = function (id) {
    fetch(`http://localhost:8080/api/pacientes/${id}`)
      .then(res => res.json())
      .then(p => {
        document.getElementById("nome").value = p.nome;
        document.getElementById("idade").value = p.idade;
        document.getElementById("responsavel").value = p.nomeResponsavel;
        document.getElementById("telefone").value = p.telefone;
        document.getElementById("endereco").value = p.endereco;
        document.getElementById("classificacao").value = p.classificacaoRisco;
        pacienteEditando = p.id;
      })
      .catch(err => alert("Erro ao carregar os dados para edição."));
  };

  // Carregar a lista de pacientes ao iniciar
  carregarPacientes();
});
