//package dao;
//
//import com.mongodb.MongoClientSettings;
//import com.mongodb.client.*;
//import com.mongodb.client.result.DeleteResult;
//import modelo.User;
//import modelo.Vehicle;
//import org.bson.Document;
//import org.bson.codecs.configuration.CodecRegistries;
//import org.bson.codecs.configuration.CodecRegistry;
//import org.bson.codecs.pojo.PojoCodecProvider;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDao{
//
//    private final MongoCollection<User> collection;
//
//
//    public UserDao(){
//        MongoDatabase mongoDB = MongoConn.mongoDB();
//        this.collection = mongoDB.getCollection("Users", User.class);
//    }
//
//    public void insertUser(User newUser) {
//        if ( newUser != null){  //
//            User exist = collection.find(new Document("cnh", newUser.getCnh())).first();
//            if (exist == null){
//                collection.insertOne(newUser);
//                System.out.println("usuario inserido");
//            }
//            else {
//                System.out.println("Erro: Usuário com a CNH " + newUser.getCnh() + " já existe.");
//            }
//        }
//    }
//
//
//    public void alterar(String chave, String valor, User novo){
//        collection.replaceOne(new Document(chave, valor), novo);
//    }
//
//    public boolean excluir(String chave, String valor){
//        Document filter = new Document(chave, valor);
//        DeleteResult result = collection.deleteOne(filter);
//        return result.getDeletedCount() > 0;
//    }
//
////    public User buscarPorChave(String chave){//Chave = cnh
////        User retorno = collection.find(new Document(chave).first();
////        return retorno;
////    }
//
//
//    public List<User> listarTodos(){
//        ArrayList<User> todos = new ArrayList();
//        MongoCursor<User> cursor = collection.find().iterator();
//        while(cursor.hasNext()){
//            User elemento = (User)cursor.next();
//            todos.add(elemento);
//        }
//        return todos;
//    }
//
//    public List<User> apagarTodos(){
//        ArrayList<User> todos = new ArrayList();
//        MongoCursor<User> cursor = collection.find().iterator();
//        while(cursor.hasNext()){
//            User elemento = (User)cursor.next();
//            todos.remove(elemento);
//        }
//        return todos;
//    }
//
//
//}
