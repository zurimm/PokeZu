from flask import Flask, send_file
import pandas as pd
import matplotlib.pyplot as plt
import pyodbc

app = Flask(__name__)


@app.route('/')
def home():
    return "Bienvenido a la aplicación de Pokémon!"


@app.route('/pokemon/report', methods=['GET'])
def get_pokemon_report():
    # Conectar a la base de datos
    conn_str = (
        'DRIVER={SQL Server};'
        'SERVER=ZURIMITCHELL;'
        'DATABASE=POKEMON_DB;'
        'UID=POKE;'
        'PWD=12345678'
    )
    conn = pyodbc.connect(conn_str)

    # Consultar datos
    query = """
    SELECT 
        t.NOMBRE AS TipoPokemon
    FROM 
        POKEMON p
    JOIN 
        TIPO_POKEMON t ON p.TIPO = t.ID
    """
    df = pd.read_sql(query, conn)
    conn.close()

    # Analizar datos
    tipo_counts = df['TipoPokemon'].value_counts()

    # Generar gráfico
    plt.figure(figsize=(10, 6))
    plt.bar(tipo_counts.index, tipo_counts.values, color='skyblue')
    plt.xlabel('Tipo de Pokémon')
    plt.ylabel('Cantidad')
    plt.title('Cantidad de Pokémon por Tipo')
    plt.xticks(rotation=45)
    plt.tight_layout()
    plt.savefig('pokemon_tipo_report.png')

    return send_file('pokemon_tipo_report.png', mimetype='image/png')


if __name__ == '__main__':
    app.run(debug=True)
