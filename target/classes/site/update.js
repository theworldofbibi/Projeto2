const corpoTabela = document.querySelector('#professores');
const paragrafoMensagem = document.querySelector('#mensagem');
const textoNome = document.querySelector('#txtNome');
const textoMatricula = document.querySelector('#txtMatricula');
const celulaID = document.querySelector('#idProfessor');

function inicializar() {
paragrafoMensagem.innerHTML = 'Selecione um professor:'
celulaID.innerHTML = '';
textoNome.value = '';
textoMatricula.value = '';
listarTodosProfessores();
}
function selecionarProfessor(id, nome, matricula) {
paragrafoMensagem.innerHTML = 'Altere os dados do professor e pressione o botão Salvar.'
celulaID.innerHTML = id;
textoNome.value = nome;
textoMatricula.value = matricula;
}
async function atualizarProfessor() {
if (celulaID.innerHTML == '') return;
const ID = celulaID.innerHTML;
const URL = `/api/professores/${ID}`;
const dadosProfessor = {
'id': ID,
'nome': textoNome.value,
'matricula': textoMatricula.value
};
const putRequest = {
method:'PUT',
body: JSON.stringify(dadosProfessor),
headers: { 'Content-type': 'application/json;charset=UTF-8' }
};
try {
fetch(URL, putRequest).then(resposta => resposta.json()).then(jsonResponse => inicializar());
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