const corpoTabela = document.querySelector('#professores');
const paragrafoMensagem = document.querySelector('#mensagem');
const celulaNome = document.querySelector('#nomeProfessor');
const celulaMatricula = document.querySelector('#matriculaProfessor');
const celulaID = document.querySelector('#idProfessor');

function inicializar() {
paragrafoMensagem.innerHTML = 'Selecione um professor:'
celulaID.innerHTML = '';
celulaNome.innerHTML = '';
celulaMatricula.innerHTML = '';
listarTodosProfessores();
}
function selecionarProfessor(id, nome, matricula) {
paragrafoMensagem.innerHTML = 'Pressione o botão Apagar para remover o professor.'
celulaID.innerHTML = id;
celulaNome.innerHTML = nome;
celulaMatricula.innerHTML = matricula;
}
async function apagarProfessor() {
if (celulaID.innerHTML == '') return;
const ID = celulaID.innerHTML;
const URL = `/api/professores/${ID}`;
const deleteRequest = {
method:'DELETE'
};
try {
fetch(URL, deleteRequest).then(resposta => inicializar());
} catch (e) {
corpoTabela.innerHTML = e;
}
}
    function preencherTabela(professores) {
var linhasTabela = '';
var n = professores.length;
for (var i = 0; i < n; i++) {
var professor = professores[i];
linhasTabela +=
`<tr><td><a href="javascript:void(0)" onclick="selecionarProfessor('${professor.id}','${professor.nome}','${professor.matricula}')">`
+ professor.id + '</a></td>' +
'<td>' + professor.nome + '</td>' +
'<td>' + professor.matricula + '</td></tr>\n';
}
corpoTabela.innerHTML = linhasTabela;
}
async function listarTodosProfessores() {
const URL = `/api/professores`;
try {
fetch(URL).then(resposta => resposta.json()).then(jsonResponse => preencherTabela(jsonResponse));
} catch (e) {
corpoTabela.innerHTML = e;
}
}
inicializar();