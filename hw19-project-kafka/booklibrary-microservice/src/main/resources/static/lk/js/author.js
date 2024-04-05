const api = '/api/library/author';
const topic = '/topic/author';

function getEntityJson() {
    return {
        id: document.getElementById('entity.id').value,
        name: document.getElementById('entity.name').value
    };
}

function fillEntityForm(json) {
    document.getElementById('entity.id').value = json.id;
    document.getElementById('entity.name').value = json.name;
}

function getEntitiesRows(json) {}

function buildEntityRow(entity) {
    return `
        <tr id='${entity.id}'>
            <td>${entity.name}</td>
            <td>
                <button onclick='editEntityDialog("${entity.id}")'>Править</button>
                <button onclick='deleteEntityDialog("${entity.id}")'>Удалить</button>
            </td>
        </tr>`;
}

function toJsonNotice(event) {
    return {
        subj: translate(event.op) + " автор",
        body: (event.after) ? event.after.name : event.before.name
    };
}
function translate(word) {
    switch(word) {
        case 'INSERT':
        case 'c': return "Добавлен(а)";
        case 'UPDATE':
        case 'u': return "Изменен(а)";
        case 'DELETE':
        case 'd': return "Удален(а)";
        default: return word;
    }
}
