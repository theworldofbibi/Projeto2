const textoSemana = document.querySelector("#txtSemana");
const textoPosicao = document.querySelector("#txtPosicao");
const paragrafoMensagem = document.querySelector("#mensagem");

textoSemana.value = '';
textoPosicao.value = '';

function mostrarMensagem(professorCadastrado) {
textoSemana.value = '';
textoPosicao.value = '';
paragrafoMensagem.innerHTML = 'Professor criado com id ' + professorCadastrado.id;
}

async function criarChart() {
const URL = "/api/charts";
const dadosCharts = {
"Week": textoSemana.value,
"Position": textoPosicao.value
};
    
const postRequest = {
method:'POST',
body: JSON.stringify(dadosCharts),
headers: { 'Content-type': 'application/json;charset=UTF-8' }
};
    try {
        fetch(URL, postRequest).then(resposta => resposta.json()).then(jsonResponse => mostrarMensagem(jsonResponse));
    } catch (e) {
    corpoTabela.innerHTML = e;
    }
    }
