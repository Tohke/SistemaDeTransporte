package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import java.util.ArrayList;
import java.util.List;

import modelo.Vehicle;
import org.bson.Document;

/**
 * Classe responsável pela persistência de objetos.
 * @param <T> Parâmetro de tipo: classe do objeto a ser persistido.
 */
public class Dao <T> {

    private final String URI = "mongodb://localhost:27017";
    private final String DATABASE = "SistemaDeTransporte";
    private final MongoCollection<T> collection;

    public Dao(Class<T> classe){
        MongoDatabase database = MongoConn.mongoDB();
        this.collection = database.getCollection(classe.getSimpleName(), classe);
    }

// Inserir Universal
    //Fazer gerar o ID e fazer o mesmo incrementar a cada cadastro de operador ou ver de utilizar o que a database utiliza
    public void insert(T object){
        if(object != null){
            collection.insertOne(object);
        }
    }
    /**
     *
     * @param key O nome do atributo pelo qual o objeto vai ser buscado, ex: codigo. A CHAVE QUE O OBJETO RECEBE
     * @param value O valor do atributo identificador do objeto a ser alterado, exemplo: 20 (vai buscar o objeto cujo código seja 20). A CHAVE QUE QUER BUSCAR
     * @param newData O objeto com os novos valores que devem substituir os antigos.
     */


    public void change(String key, Object value, T newData) {
        collection.replaceOne(new Document(key, value), newData);
    }


    /**
     * Apaga um objeto no banco.
     * @param key O nome do atributo pelo qual o objeto vai ser buscado, ex: codigo.
     * @param value O value do atributo identificador do objeto a ser alterado, exemplo: 20 (vai excluir o objeto cujo código seja 20).
     * @return True se um objeto foi excluído ou false caso contrário.
     */


    public boolean delete(String key, Object value){
        DeleteResult result = collection.deleteOne(new Document(key, value));
        return result.getDeletedCount() > 0;
    }

    /**
     * Retorna o objeto cuja chave for igual ao valor passado.
     * @param key o campo pelo qual o objeto vai ser buscado
     * @param value o valor da chave
     * @return O objeto correspondente à chave ou null caso não exista.
     */
    public T buscarPorChave(String key, Object value) {
        return collection.find(new Document(key, value)).first();
    }



    public void insertData(Vehicle newData) {
        if ( newData == null){  // não existe
            //collection.insertOne(newVehicle);
            System.out.println("nqbrenbqneb0");
        }
        else {
            System.out.println("Já existe");
        }
    }

    /**
     * Retorna todos os objetos de uma coleção do tipo T.
     *
     */
    public List<T> listAll() {
        ArrayList<T> allData = new ArrayList<>();
        MongoCursor<T> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                allData.add(cursor.next());
            }
        } finally {
            cursor.close();
        }
        return allData;
    }
}