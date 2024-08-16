
const express = require('express');
const path = require('path');
const { executeTipoPokemon, 
        executePokemones, 
        executeHabilidades, 
        executePokemonesPorNombre, 
        executecrearPOKEMON, 
        executeeliminaPOKEMON,
        executeBitacoraTotal, 
        executeRegistroBitacora 
    } = require('./Conexion');

const app = express();
const port = 3000;

app.use(express.json());
app.use(express.static(path.join(__dirname, '../html'))); // Servir archivos estáticos desde la carpeta 'public'

// Endpoint para consultar todos los Pokémon

app.get('/pokemones', (req, res) => {
    executePokemones()
        .then(data => res.json(data))
        .catch(err => res.status(500).json({ error: err.message }));
});
/*
app.get('/pokemones', (req, res) => {
    executePokemones()
        .then(data => {
            console.log('Data returned:', data);  // Agrega un log para verificar los datos
            res.json(data);
        })
        .catch(err => {
            console.error('Error:', err);  // Agrega un log para errores
            res.status(500).json({ error: err.message });
        });
});
*/
// Endpoint para consultar tipos de Pokémon
app.get('/tipos-pokemon', (req, res) => {
    executeTipoPokemon()
        .then(data => res.json(data))
        .catch(err => res.status(500).json({ error: err.message }));
});

// Endpoint para consultar habilidades
app.get('/habilidades', (req, res) => {
    executeHabilidades()
        .then(data => res.json(data))
        .catch(err => res.status(500).json({ error: err.message }));
});

// Endpoint para consultar Pokémon por nombre
app.get('/pokemones/:nombre', (req, res) => {
    const { nombre } = req.params;
    executePokemonesPorNombre(nombre)
        .then(data => res.json(data))
        .catch(err => res.status(500).json({ error: err.message }));
});

// Endpoint para crear Pokémon
/* 
app.post('/crearPOKEMON', (req, res) => {
    
    const { nombre, tipo, vida, ataque, defensa, resistencia } = req.body;
    console.log('Servidor: ' + this.nombre, this.tipo, this.ataque, this.defensa, this.resistencia);

    executecrearPOKEMON(nombre, tipo, vida, ataque, defensa, resistencia)

        .then(data => res.json(data))
        .catch(err => res.status(500).json({ error: err.message }));
});*/
// Endpoint para crear Pokémon
app.post('/crearPOKEMON', (req, res) => {
    const { nombre, tipo, vida, ataque, defensa, resistencia } = req.body;

    console.log('Servidor:', nombre, tipo, vida, ataque, defensa, resistencia);

    executecrearPOKEMON(nombre, tipo, vida, ataque, defensa, resistencia)
        .then(data => res.json(data))
        .catch(err => res.status(500).json({ error: err.message }));
});

// Endpoint para eliminar un Pokémon
/*
app.delete('/eliminarPOKEMON', (req, res) => {
    const { nombre } = req.body; 
    console.log("Eliminar3: " + nombre);
    executeeliminaPOKEMON(nombre)
    .then(data => res.json(data))
    .catch(err => res.status(500).json({ error: err.message }));
});
*/

app.delete('/eliminarPOKEMON', (req, res) => {
    const nombre = req.query.nombre; 
    console.log("Eliminar3: " + nombre);
    executeeliminaPOKEMON(nombre)
    .then(data => res.json(data))
    .catch(err => res.status(500).json({ error: err.message }));
});



// Endpoint para consultar Bitacora
app.get('/BitacoraTotal', (req, res) => {
    executeBitacoraTotal()
        .then(data => res.json(data))
        .catch(err => res.status(500).json({ error: err.message }))
       
});

// Endpoint para registro en bitacora
app.post('/crearRegistroBitacora', (req, res) => {
    
    const {NOMBRE, ACCION, Fecha} = req.body;


    executeRegistroBitacora(NOMBRE, ACCION, Fecha)

        .then(data => res.json(data))
        .catch(err => res.status(500).json({ error: err.message }));
});

// Servir index.html cuando se acceda a la raíz
/*
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, '../html', 'pokedex.html'));
});
*/

app.get('/', (req, res) => {
    res.send('Servidor en funcionamiento');
});
app.listen(port, () => {
    console.log(`Server is running on http://localhost:${port}`);
});

// Manejo de rutas no definidas
app.use((req, res) => {
    res.status(404).send('Ruta no encontrada');
});