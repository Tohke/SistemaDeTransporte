package dao;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 * Classe responsável pela persistência de objetos.
 * @param <T> Parâmetro de tipo: classe do objeto a ser persistido.
 */
public class Dao <T> {

    private final String URI = "mongodb://localhost:27017";
    private final String DATABASE = "SistemaDeTransporte";
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final String Vehicles;  // nome da coleção
    //private final String Users;
    private final MongoCollection<T> collection;

    public Dao(Class<T> classe){
        this.Vehicles = classe.getName();
        mongoClient = MongoClients.create(URI);
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                        CodecRegistries.
                                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        database = mongoClient.getDatabase(DATABASE).withCodecRegistry(pojoCodecRegistry);
        collection = database.getCollection(Vehicles, classe);
    }

    /**
     *
     * @param chave O nome do atributo pelo qual o objeto vai ser buscado, ex: codigo. A CHAVE QUE O OBJETO RECEBE
     * @param valor O valor do atributo identificador do objeto a ser alterado, exemplo: 20 (vai buscar o objeto cujo código seja 20). A CHAVE QUE QUER BUSCAR
     * @param novo O objeto com os novos valores que devem substituir os antigos.
     */
    public void alterar(String chave, String valor, T novo){
        collection.replaceOne(new Document(chave, valor), novo);
    }


    /**
     * Apaga um objeto no banco.
     * @param chave O nome do atributo pelo qual o objeto vai ser buscado, ex: codigo.
     * @param valor O valor do atributo identificador do objeto a ser alterado, exemplo: 20 (vai excluir o objeto cujo código seja 20).
     * @return True se um objeto foi excluído ou false caso contrário.
     */
    public boolean excluir(String chave, String valor){
        Document filter = new Document(chave, valor);
        DeleteResult result = collection.deleteOne(filter);
        return result.getDeletedCount() > 0;
    }

    /**
     * Retorna o objeto cuja chave for igual ao valor passado.
     * @param chave o campo pelo qual o objeto vai ser buscado
     * @param valor o valor da chave
     * @return O objeto correspondente à chave ou null caso não exista.
     */
    public T buscarPorChave(String chave, String valor){
        T retorno = collection.find(new Document(chave, valor)).first();
        return retorno;
    }

    public void inserir(T objeto){
        collection.insertOne(objeto);
    }


    /**
     * Retorna todos os objetos de uma coleção do tipo T.
     * @return
     */
    public List<T> listarTodos(){
        ArrayList<T> todos = new ArrayList();
        MongoCursor<T> cursor = collection.find().iterator();
        while(cursor.hasNext()){
            T elemento = (T)cursor.next();
            todos.add(elemento);
        }
        return todos;
    }


}