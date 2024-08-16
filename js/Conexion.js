const { Connection, Request, TYPES } = require('tedious');

const config = {
    server: "ZURIMITCHELL",
    authentication: {
        type: "default",
        options:{ 
            userName:"POKE",
            password: "12345678"
        }
    },
    options:{
        port: 1433,
        database: "POKEMON_DB",
        trustServerCertificate: true
    }
};

function connect() {
    return new Promise((resolve, reject) => {
        const connection = new Connection(config);
        connection.on('connect', (err) => {
            if (err) {
                reject(err);
            } else {
                resolve(connection);
            }
        });
        connection.connect();
    });
}

function executeQuery(query, parameters = []) {
    return new Promise(async (resolve, reject) => {
        const connection = await connect();
        const results = [];
        const request = new Request(query, (err, rowCount) => {
            if (err) {
                reject(err);
            } else {
                resolve(results);
                connection.close();
            }
        });

        parameters.forEach(param => {
            request.addParameter(param.name, param.type, param.value);
        });

        request.on('row', columns => {
            const result = {};
            columns.forEach(column => {
                result[column.metadata.colName] = column.value;
            });
            results.push(result);
        });

        connection.execSql(request);
    });
}

function executePokemones() {
    return executeQuery("SELECT * FROM POKEMON");
}
/*
function executePokemones() {
    return pool.query('SELECT * FROM POKEMON')
        .then(result => result.rows)
        .catch(err => { throw err; });
}*/

function executeTipoPokemon() {
    return executeQuery("SELECT * FROM TIPO_POKEMON");
}

function executeHabilidades() {
    return executeQuery("SELECT * FROM HABILIDADES_POKEMON");
}

function executePokemonesPorNombre(nombre) {
    return executeQuery("SELECT * FROM POKEMON WHERE Nombre = @Nombre", [
        { name: 'Nombre', type: TYPES.NVarChar, value: nombre }
    ]);
}

function executecrearPOKEMON(nombre, tipo, vida, PUNTAJE_ATAQUE, PUNTAJE_DEFENSA, PUNTAJE_RESISTENCIA) {
    var intTipo = parseInt(tipo);
    var intvida = parseInt(vida);
    var intPUNTAJE_ATAQUE = parseInt(PUNTAJE_ATAQUE);
    var intPUNTAJE_DEFENSA = parseInt(PUNTAJE_DEFENSA);
    var intPUNTAJE_RESISTENCIA = parseInt(PUNTAJE_RESISTENCIA);

    console.log(nombre);
    console.log(intTipo);
    console.log(intvida);
    console.log(intPUNTAJE_ATAQUE);
    console.log(intPUNTAJE_DEFENSA);
    console.log(intPUNTAJE_RESISTENCIA);

    return executeQuery("INSERT INTO POKEMON (NOMBRE, VIDA, TIPO, PUNTAJE_ATAQUE, PUNTAJE_DEFENSA, PUNTAJE_RESISTENCIA) VALUES (@nombre, @intvida, @intTipo, @intPUNTAJE_ATAQUE, @intPUNTAJE_DEFENSA, @intPUNTAJE_RESISTENCIA)", [
        { name: 'nombre', type: TYPES.NVarChar, value: nombre },
        { name: 'intvida', type: TYPES.Int, value: intvida },
        { name: 'intTipo', type: TYPES.Int, value: intTipo },
        { name: 'intPUNTAJE_ATAQUE', type: TYPES.Int, value: intPUNTAJE_ATAQUE },
        { name: 'intPUNTAJE_DEFENSA', type: TYPES.Int, value: intPUNTAJE_DEFENSA },
        { name: 'intPUNTAJE_RESISTENCIA', type: TYPES.Int, value: intPUNTAJE_RESISTENCIA }
    ]);
}
function executeeliminaPOKEMON(Nombre) {
    console.log("Eliminar4: " + Nombre);
    return executeQuery("DELETE FROM POKEMON WHERE Nombre = @Nombre", [
        { name: 'Nombre', type: TYPES.NVarChar, value: Nombre }
    ]);
}


// *** Logs CAMBIOS ********************************************************************************************* //
function executeBitacoraTotal() {
    return executeQuery("SELECT * FROM BITACORA_CAMBIOS_POKEMONES");
}


//DESMENUSA LA FECHA PARA QUE LO PUEDA LEER EL SQL SERVER
function getFormattedDate() {
    const now = new Date();
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, '0'); // Months are 0-based
    const day = String(now.getDate()).padStart(2, '0');
    const hours = St0ring(now.getHours()).padStart(2, '0');
    const minutes = String(now.getMinutes()).padStart(2, '0');
    const seconds = String(now.getSeconds()).padStart(2, '0');

    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}

function executeRegistroBitacora(NOMBRE, ACCION) {
    var Fecha = getFormattedDate(); // Obtener fecha formateada

    return executeQuery("INSERT INTO BITACORA_CAMBIOS_POKEMONES (NOMBRE, ACCION, FECHA) VALUES (@NOMBRE, @ACCION, @Fecha)", [
        { name: 'NOMBRE', type: TYPES.NVarChar, value: NOMBRE },
        { name: 'ACCION', type: TYPES.NVarChar, value: ACCION },
        { name: 'Fecha', type: TYPES.DateTime, value: Fecha }
    ]);
}
module.exports = { executeTipoPokemon, executePokemones, executeHabilidades, executePokemonesPorNombre, executecrearPOKEMON, executeeliminaPOKEMON, executeBitacoraTotal, executeRegistroBitacora };
