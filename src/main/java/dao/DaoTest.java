
//package dao;


import modelo.Vehicle;
import java.util.List;

/**
 *
 * @author Usuário
 */
//public class DaoTest {
//    public DaoTest() {}
//

//    public void testInserir() {
//        Vehicle v = new Vehicle("TDFA9865", "Ford", "Mustang");
//        Dao<Vehicle> dao = new Dao<>(Vehicle.class);
//        Vehicle aux = dao.buscarPorChave("placa", "TDFA9865");
//        if ( aux == null){  // não existe
//            dao.inserir(v);
//        }
//        else{
//            System.out.println("Já existe");
//        }
//    }

//    public void testListar(){
//        List<Vehicle> lista;
//        Dao<Vehicle> dao = new Dao<>(Vehicle.class);
//        lista = dao.listarTodos();
//        for (Vehicle v : lista){
//            System.out.println(v.getPlaca());
//        }
//    }
//}