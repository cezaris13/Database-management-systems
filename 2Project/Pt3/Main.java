import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Scanner;
import java.lang.Exception;

public class Main{
    public static void loadDriver(){
        try{
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException cnfe){
            System.out.println("Couldn't find driver class!");
            cnfe.printStackTrace();
            System.exit(1);
        }
    }

    public static void showProviders(Connection connection){
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = connection.prepareStatement("SELECT * from pipe7709.Tiekejas");

            resultSet=statement.executeQuery();
            if(resultSet.next()){
                System.out.println("TiekejoNr, Pavadinimas, TelefonoNumeris, ElPastas, Puslapis");
                do{
                    System.out.println(resultSet.getString(1)+","+resultSet.getString(2)+","+resultSet.getString(3)+","+resultSet.getString(4)+","+resultSet.getString(5));
                }while(resultSet.next());
            }
            else{
                System.out.println("No providers found");
            }
        }
        catch (SQLException e){
            System.out.println("SQL Error!");
            e.printStackTrace();
        }
        finally{
            try{
                if(null != resultSet)
                    resultSet.close();
                if(null != statement)
                    statement.close();
            }
            catch (SQLException exp){
                System.out.println("Unexpected SQL Error while closing statement!");
                exp.printStackTrace();
            }
        }
    }

    public static void showDrinks(Connection connection){
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = connection.prepareStatement("SELECT * from pipe7709.KavaArbata");

            resultSet=statement.executeQuery();
            if(resultSet.next()){
                System.out.println("GerimoNr, TiekejoNr, Pavadinimas, TiekejoKaina, PapildomaInformacija");
                do{
                    System.out.println(resultSet.getString(1)+","+resultSet.getString(2)+","+resultSet.getString(3)+","+resultSet.getString(4)+","+resultSet.getString(5));
                }while(resultSet.next());
            }
            else{
                System.out.println("No drinks were found");
            }
        }
        catch (SQLException e){
            System.out.println("SQL Error!");
            e.printStackTrace();
        }
        finally{
            try{
                if(null != resultSet)
                    resultSet.close();
                if(null != statement)
                    statement.close();
            }
            catch (SQLException exp){
                System.out.println("Unexpected SQL Error while closing statement!");
                exp.printStackTrace();
            }
        }
    }

    public static void showEquipment(Connection connection){
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = connection.prepareStatement("SELECT * from pipe7709.Iranga");

            resultSet=statement.executeQuery();
            if(resultSet.next()){
                System.out.println("IrangosNr, TiekejoNr, Pavadinimas, TiekejoKaina, Talpa");
                do{
                    System.out.println(resultSet.getString(1)+","+resultSet.getString(2)+","+resultSet.getString(3)+","+resultSet.getString(4)+","+resultSet.getString(5));
                }while(resultSet.next());
            }
            else{
                System.out.println("No equipment found");
            }
        }
        catch (SQLException e){
            System.out.println("SQL Error!");
            e.printStackTrace();
        }
        finally{
            try{
                if(null != resultSet)
                    resultSet.close();
                if(null != statement)
                    statement.close();
            }
            catch (SQLException exp){
                System.out.println("Unexpected SQL Error while closing statement!");
                exp.printStackTrace();
            }
        }
    }

    public static void showShops(Connection connection){
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = connection.prepareStatement("SELECT * from pipe7709.Parduotuve");

            resultSet=statement.executeQuery();
            if(resultSet.next()){
                System.out.println("ParduotuvesNr, Salis, Miestas, Gatve, DarboPradzia, DarboPabaiga");
                do{
                    System.out.println(resultSet.getString(1)+","+resultSet.getString(2)+","+resultSet.getString(3)+","+resultSet.getString(4)+","+resultSet.getString(5)+","+resultSet.getString(6));
                }while(resultSet.next());
            }
            else{
                System.out.println("No shops found");
            }
        }
        catch (SQLException e){
            System.out.println("SQL Error!");
            e.printStackTrace();
        }
        finally{
            try{
                if(null != resultSet)
                    resultSet.close();
                if(null != statement)
                    statement.close();
            }
            catch (SQLException exp){
                System.out.println("Unexpected SQL Error while closing statement!");
                exp.printStackTrace();
            }
        }
    }

    public static void showShopsEquipment(Connection connection){
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = connection.prepareStatement("SELECT * from pipe7709.ParduotuvesIranga");

            resultSet=statement.executeQuery();
            if(resultSet.next()){
                System.out.println("ParduotuvesNr, IrangosNr, Kiekis");
                do{
                    System.out.println(resultSet.getString(1)+","+resultSet.getString(2)+","+resultSet.getString(3));
                }while(resultSet.next());
            }
            else{
                System.out.println("No shops equipment were found");
            }
        }
        catch (SQLException e){
            System.out.println("SQL Error!");
            e.printStackTrace();
        }
        finally{
            try{
                if(null != resultSet)
                    resultSet.close();
                if(null != statement)
                    statement.close();
            }
            catch (SQLException exp){
                System.out.println("Unexpected SQL Error while closing statement!");
                exp.printStackTrace();
            }
        }
    }

    public static void showShopsDrinks(Connection connection){
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = connection.prepareStatement("SELECT * from pipe7709.ParduotuvesGerimas");

            resultSet=statement.executeQuery();
            if(resultSet.next()){
                System.out.println("ParduotuvesNr, GerimoNr, Kiekis");
                do{
                    System.out.println(resultSet.getString(1)+","+resultSet.getString(2)+","+resultSet.getString(3));
                }while(resultSet.next());
            }
            else{
                System.out.println("No shop drinks found");
            }
        }
        catch (SQLException e){
            System.out.println("SQL Error!");
            e.printStackTrace();
        }
        finally{
            try{
                if(null != resultSet)
                    resultSet.close();
                if(null != statement)
                    statement.close();
            }
            catch (SQLException exp){
                System.out.println("Unexpected SQL Error while closing statement!");
                exp.printStackTrace();
            }
        }
    }

    public static void findDrink(Connection connection,String drinkName){
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = connection.prepareStatement("SELECT * from pipe7709.kavaArbata WHERE pipe7709.kavaArbata.Pavadinimas = ?");
            statement.setString(1, drinkName);

            resultSet = statement.executeQuery();
            if(resultSet.next()){
                System.out.println("GerimoNr, TiekejoNr, Pavadinimas, TiekejoKaina, PapildomaInformacija");
                System.out.println(resultSet.getString(1)+","+resultSet.getString(2)+","+resultSet.getString(3)+","+resultSet.getString(4)+","+resultSet.getString(5));
            }
            else{
                System.out.println("No drinks by the name: "+ drinkName + " were found.");
            }
        }
        catch (SQLException e){
            System.out.println("SQL Error!");
            e.printStackTrace();
        }
        finally{
            try{
                if(null != resultSet)
                    resultSet.close();
                if(null != statement)
                    statement.close();
            }
            catch (SQLException exp){
                System.out.println("Unexpected SQL Error while closing statement!");
                exp.printStackTrace();
            }
        }
    }

    public static void showCoffeeTeaInTheShop(Connection connection, int shopId){
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = connection.prepareStatement("SELECT KA.*, PG.Kiekis as GerimoKiekis " +
                    "FROM pipe7709.KavaArbata as KA, pipe7709.ParduotuvesGerimas as PG, pipe7709.Parduotuve as P "+
                    "WHERE P.Nr = ? AND P.Nr = PG.ParduotuvesNr AND PG.GerimoNr = KA.Nr");
            statement.setInt(1, shopId);

            resultSet = statement.executeQuery();
            if(resultSet.next()){
                System.out.println("GerimoNr, TiekejoNr, Pavadinimas, TiekejoKaina, PapildomaInformacija,GerimoKiekis");
                do{
                    System.out.println(resultSet.getString(1)+","+resultSet.getString(2)+","+resultSet.getString(3)+","+resultSet.getString(4)+","+resultSet.getString(5)+","+resultSet.getString(6));
                }while(resultSet.next());
            }
            else{
                System.out.println("No shops,drinks or shop drinks were found");
            }
        }
        catch (SQLException e){
            System.out.println("SQL Error!");
            e.printStackTrace();
        }
        finally{
            try{
                if(null != resultSet)
                    resultSet.close();
                if(null != statement)
                    statement.close();
            }
            catch (SQLException exp){
                System.out.println("Unexpected SQL Error while closing statement!");
                exp.printStackTrace();
            }
        }
    }

    public static void findEquipment(Connection connection,String equipmentName){
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = connection.prepareStatement("SELECT * from pipe7709.Iranga WHERE pipe7709.Iranga.Pavadinimas = ?");
            statement.setString(1, equipmentName);

            resultSet = statement.executeQuery();
            if(resultSet.next()){
                System.out.println("IrangosNr, TiekejoNr, Pavadinimas, TiekejoKaina, Talpa");
                System.out.println(resultSet.getString(1)+","+resultSet.getString(2)+","+resultSet.getString(3)+","+resultSet.getString(4)+","+resultSet.getString(5));
            }
            else{
                System.out.println("No equipment found.");
            }
        }
        catch (SQLException e){
            System.out.println("SQL Error!");
            e.printStackTrace();
        }
        finally{
            try{
                if(null != resultSet)
                    resultSet.close();
                if(null != statement)
                    statement.close();
            }
            catch (SQLException exp){
                System.out.println("Unexpected SQL Error while closing statement!");
                exp.printStackTrace();
            }
        }
    }

    public static void addNewShop(Connection connection, String country,String city, String street, String bTime, String eTime){
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement("INSERT INTO pipe7709.Parduotuve (Salis,Miestas,Gatve,DarboPradzia,DarboPabaiga)" +
                    " VALUES (?,?,?,?,?)");
            var startTime = Time.valueOf(bTime);
            var endTime = Time.valueOf(eTime);
            statement.setString(1, country);
            statement.setString(2, city);
            statement.setString(3, street);
            statement.setTime(4, startTime);
            statement.setTime(5, endTime);
            statement.execute();
        }
        catch (SQLException e){
            System.out.println("SQL Error!");
            e.printStackTrace();
        }
        catch (IllegalArgumentException e){
            System.out.println("The given date(s) were invalid");
        }
        finally{
            try{
                if(null != statement)
                    statement.close();
            }
            catch (SQLException exp){
                System.out.println("Unexpected SQL Error!");
                exp.printStackTrace();
            }
        }
    }

    public static void updateDrink(Connection connection, int id, String name){
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement("UPDATE pipe7709.kavaArbata" +
                    " SET Pavadinimas = ? WHERE Nr = ?" );
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("SQL Error!");
            e.printStackTrace();
        }
        finally{
            try{
                if(null != statement)
                    statement.close();
            }
            catch (SQLException exp){
                System.out.println("Unexpected SQL Error!");
                exp.printStackTrace();
            }
        }
    }

    public static void removeDrink(Connection connection, int id){
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement("DELETE FROM pipe7709.kavaArbata" +
                    " WHERE pipe7709.kavaArbata.nr = ?" );
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("SQL Error!");
            e.printStackTrace();
        }
        finally{
            try{
                if(null != statement)
                    statement.close();
            }
            catch (SQLException exp){
                System.out.println("Unexpected SQL Error!");
                exp.printStackTrace();
            }
        }
    }

    public static Connection getConnection(){
        SensitiveData data = new SensitiveData();
        Connection postGresConn = null;
        String user = data.userName;
        String password = data.password;
        try{
            postGresConn = DriverManager.getConnection("jdbc:postgresql://pgsql2.mif/studentu", user, password);
        }
        catch (SQLException sqle){
            System.out.println("Couldn't connect to database!");
            sqle.printStackTrace();
            return null;
        }
        System.out.println("Successfully connected to Postgres Database");
        return postGresConn;
    }

    public static void showUsers(Connection connection){
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = connection.prepareStatement("SELECT * FROM pipe7709.Pirkejai");

            resultSet = statement.executeQuery();
            if(resultSet.next()){
                System.out.println("PirkejoNr,vardas,pavarde,piniguKiekisPaskyroje");
                do{
                    System.out.println(resultSet.getString(1)+","+resultSet.getString(2)+","+resultSet.getString(3)+","+resultSet.getString(4));
                }while(resultSet.next());
            }
            else{
                System.out.println("No users were found");
            }
        }
        catch (SQLException e){
            System.out.println("SQL Error!");
            e.printStackTrace();
        }
        finally{
            try{
                if(null != statement)
                    statement.close();
            }
            catch (SQLException exp){
                System.out.println("Unexpected SQL Error!");
                exp.printStackTrace();
            }
        }
    }

    public static void buyDrinkForAUser(Connection connection, int kiekis, int gerimoNr, int userNr, int parduotuvesNr){
        String updateString =
            "update pipe7709.Pirkejai set PiniguKiekisPaskyroje = PiniguKiekisPaskyroje - ? where Nr = ?";
        String updateStatement =
            "update pipe7709.ParduotuvesGerimas set Kiekis = Kiekis - ? where GerimoNr = ? AND ParduotuvesNr = ?";

        try (PreparedStatement updateSales = connection.prepareStatement(updateString);
                PreparedStatement updateTotal = connection.prepareStatement(updateStatement)){

            PreparedStatement statement = connection.prepareStatement("SELECT TiekejoKaina from pipe7709.KavaArbata WHERE pipe7709.KavaArbata.Nr = ?");
            statement.setInt(1, gerimoNr);
            float cost=0;
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                cost = resultSet.getFloat(1);
                System.out.println(cost);
            }
            else{
                System.out.println("No drinks by id: "+gerimoNr+" were found.");
                return;
            }
            connection.setAutoCommit(false);
            updateSales.setFloat(1, kiekis*cost);
            updateSales.setInt(2, userNr);
            updateSales.executeUpdate();

            updateTotal.setInt(1, kiekis);
            updateTotal.setInt(2, gerimoNr);
            updateTotal.setInt(3, parduotuvesNr);
            updateTotal.executeUpdate();
            connection.commit();
                }
        catch (SQLException e){
            try{
                if(connection!=null){
                    System.err.println("Transaction is being rolled back");
                    connection.rollback();
                }
            }
            catch (SQLException excep){
            }
        }
    }

    public static void main(String[] args){
        loadDriver();
        Connection connection = getConnection();
        if(null != connection){
            Scanner scanner = new Scanner(System.in);
            System.out.println("The system is starting...");

            int method = 0;
            while(method >= 0 && method < 9){
                switch(method){
                    case 0:
                        System.out.println("Menu options:\n* Press 0 to see the menu.\n" + 
                                "* Press 1 to find drink.\n" +
                                "* Press 2 to update the drink.\n" + 
                                "* Press 3 to remove the drink.\n" + 
                                "* Press 4 to find equipment.\n" + 
                                "* Press 5 to add new shop.\n" + 
                                "* Press 6 to see drinks in the shop.\n" + 
                                "* Press 7 to do buy drink for a user.\n" + 
                                "* Press 8 to show Users.\n" + 
                                "* Press 9 to exit");
                        break;
                    case 1:
                        System.out.println("Input drink name");
                        String drink = scanner.nextLine();
                        findDrink(connection, drink);
                        break;
                    case 2:
                        try{
                            showDrinks(connection);
                            System.out.println("Input drink id");
                            int drinkId1 = Integer.parseInt(scanner.nextLine());
                            System.out.println("Input new drink name");
                            String newDrinkName = scanner.nextLine();
                            updateDrink(connection,drinkId1,newDrinkName);
                        }
                        catch(NumberFormatException ex){
                            System.out.println("Provided id is not valid.");
                        }
                        finally{
                            break;
                        }
                    case 3:
                        try{
                            showDrinks(connection);
                            System.out.println("Input drink id");
                            int drinkId = Integer.parseInt(scanner.nextLine());
                            removeDrink(connection, drinkId);
                        }
                        catch(NumberFormatException ex){
                            System.out.println("Provided id is not valid.");
                        }
                        break;
                    case 4:
                        System.out.println("Input equipment name");
                        String equipment = scanner.nextLine();
                        findEquipment(connection, equipment);
                        break;
                    case 5:
                        System.out.println("Input country name");
                        String country = scanner.nextLine();
                        System.out.println("Input city");
                        String city = scanner.nextLine();
                        System.out.println("Input street");
                        String street = scanner.nextLine();
                        System.out.println("Input shop start time");
                        String startTime = scanner.nextLine();
                        System.out.println("Input shop end time");
                        String endTime = scanner.nextLine();
                        addNewShop(connection,country,city,street,startTime,endTime);
                        break;
                    case 6:
                        try{
                            showShops(connection);
                            System.out.println("Input shop id");
                            int shopId = Integer.parseInt(scanner.nextLine());
                            showCoffeeTeaInTheShop(connection,shopId);
                        }
                        catch(NumberFormatException ex){
                            System.out.println("Provided id is not valid.");
                        }
                        break;
                    case 7:
                        try{
                            showShops(connection);
                            System.out.println("Input shop id");
                            int shopId = Integer.parseInt(scanner.nextLine());
                            showCoffeeTeaInTheShop(connection,shopId);
                            System.out.println("Input drink id");
                            int drinkId = Integer.parseInt(scanner.nextLine());
                            System.out.println("Input amount");
                            int amount = Integer.parseInt(scanner.nextLine());
			    if(amount<=0){
				System.out.println("Amount cannot be negative");
				break;
			    }
                            showUsers(connection);
                            int userId = Integer.parseInt(scanner.nextLine());
                            buyDrinkForAUser(connection,amount,drinkId,userId,shopId);
                        }
                        catch(NumberFormatException ex){
                            System.out.println("Provided id is not valid.");
                        }
                        break;
                    case 8:
                        showUsers(connection);
                        break;
                    default:
                        break;
                }
                method = scanner.nextInt();
                scanner.nextLine();
            }
            scanner.close();
        }
        if( null != connection ){
            try{
                connection.close();
            }
            catch (SQLException exp){
                System.out.println("Can not close connection!");
                exp.printStackTrace();
            }
        }
    }
}
