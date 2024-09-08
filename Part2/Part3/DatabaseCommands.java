import java.sql.*;

public class DatabaseCommands {

    public Connection connection;

    public DatabaseCommands() {
        loadDriver();
        this.connection = getConnection();
    }

    public void showProviders() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT * from pipe7709.Tiekejas");
            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("No providers found");
                return;
            }

            System.out.println("TiekejoNr, Pavadinimas, TelefonoNumeris, ElPastas, Puslapis");
            printResults(resultSet, 5);
        } catch (SQLException ex) {
            System.out.println("SQL Error!");
            ex.printStackTrace();
        } finally {
            closeStatement(statement, resultSet);
        }
    }

    public void showDrinks() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT * from pipe7709.KavaArbata");
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("No drinks were found");
                return;
            }

            System.out.println("GerimoNr, TiekejoNr, Pavadinimas, TiekejoKaina, PapildomaInformacija");
            printResults(resultSet, 5);
        } catch (SQLException ex) {
            System.out.println("SQL Error!");
            ex.printStackTrace();
        } finally {
            closeStatement(statement, resultSet);
        }
    }

    public void showEquipment() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT * from pipe7709.Iranga");
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("No equipment found");
                return;
            }

            System.out.println("IrangosNr, TiekejoNr, Pavadinimas, TiekejoKaina, Talpa");
            printResults(resultSet, 5);
        } catch (SQLException ex) {
            System.out.println("SQL Error!");
            ex.printStackTrace();
        } finally {
            closeStatement(statement, resultSet);
        }
    }

    public void showShops() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT * from pipe7709.Parduotuve");
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("No shops found");
                return;
            }

            System.out.println("ParduotuvesNr, Salis, Miestas, Gatve, DarboPradzia, DarboPabaiga");
            printResults(resultSet, 6);
        } catch (SQLException ex) {
            System.out.println("SQL Error!");
            ex.printStackTrace();
        } finally {
            closeStatement(statement, resultSet);
        }
    }

    public void showShopsEquipment() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT * from pipe7709.ParduotuvesIranga");
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("No shops equipment were found");
                return;
            }

            System.out.println("ParduotuvesNr, IrangosNr, Kiekis");
            printResults(resultSet, 3);
        } catch (SQLException ex) {
            System.out.println("SQL Error!");
            ex.printStackTrace();
        } finally {
            closeStatement(statement, resultSet);
        }
    }

    public void showShopsDrinks() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT * from pipe7709.ParduotuvesGerimas");

            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("No shop drinks found");
                return;
            }
            System.out.println("ParduotuvesNr, GerimoNr, Kiekis");
            printResults(resultSet, 3);
        } catch (SQLException ex) {
            System.out.println("SQL Error!");
            ex.printStackTrace();
        } finally {
            closeStatement(statement, resultSet);
        }
    }

    public void findDrink(String drinkName) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection
                    .prepareStatement("SELECT * from pipe7709.kavaArbata WHERE pipe7709.kavaArbata.Pavadinimas = ?");
            statement.setString(1, drinkName);
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("No drinks by the name: " + drinkName + " were found.");
                return;
            }

            System.out.println("GerimoNr, TiekejoNr, Pavadinimas, TiekejoKaina, PapildomaInformacija");
            printResults(resultSet, 5);
        } catch (SQLException ex) {
            System.out.println("SQL Error!");
            ex.printStackTrace();
        } finally {
            closeStatement(statement, resultSet);
        }
    }

    public void showCoffeeTeaInTheShop(int shopId) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT KA.*, PG.Kiekis as GerimoKiekis " +
                    "FROM pipe7709.KavaArbata as KA, pipe7709.ParduotuvesGerimas as PG, pipe7709.Parduotuve as P " +
                    "WHERE P.Nr = ? AND P.Nr = PG.ParduotuvesNr AND PG.GerimoNr = KA.Nr");
            statement.setInt(1, shopId);
            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("No shops,drinks or shop drinks were found");
                return;
            }

            System.out.println("GerimoNr, TiekejoNr, Pavadinimas, TiekejoKaina, PapildomaInformacija,GerimoKiekis");
            printResults(resultSet, 6);
        } catch (SQLException ex) {
            System.out.println("SQL Error!");
            ex.printStackTrace();
        } finally {
            closeStatement(statement, resultSet);
        }
    }

    public void findEquipment(String equipmentName) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection
                    .prepareStatement("SELECT * from pipe7709.Iranga WHERE pipe7709.Iranga.Pavadinimas = ?");
            statement.setString(1, equipmentName);
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("No equipment found.");
                return;
            }

            System.out.println("IrangosNr, TiekejoNr, Pavadinimas, TiekejoKaina, Talpa");
            System.out.println(resultSet.getString(1) + "," + resultSet.getString(2) + "," + resultSet.getString(3)
                    + "," + resultSet.getString(4) + "," + resultSet.getString(5));
        } catch (SQLException ex) {
            System.out.println("SQL Error!");
            ex.printStackTrace();
        } finally {
            closeStatement(statement, resultSet);
        }
    }

    public void addNewShop(String country, String city, String street, String bTime,
            String eTime) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO pipe7709.Parduotuve (Salis,Miestas,Gatve,DarboPradzia,DarboPabaiga)" +
                            " VALUES (?,?,?,?,?)");
            var startTime = Time.valueOf(bTime);
            var endTime = Time.valueOf(eTime);
            statement.setString(1, country);
            statement.setString(2, city);
            statement.setString(3, street);
            statement.setTime(4, startTime);
            statement.setTime(5, endTime);
            statement.execute();
        } catch (SQLException sqlEx) {
            System.out.println("SQL Error!");
            sqlEx.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("The given date(s) were invalid");
        } finally {
            closeStatement(statement, null);
        }
    }

    public void updateDrink(int id, String name) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("UPDATE pipe7709.kavaArbata" +
                    " SET Pavadinimas = ? WHERE Nr = ?");
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Error!");
            ex.printStackTrace();
        } finally {
            closeStatement(statement, null);
        }
    }

    public void removeDrink(int id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM pipe7709.kavaArbata" +
                    " WHERE pipe7709.kavaArbata.nr = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Error!");
            ex.printStackTrace();
        } finally {
            closeStatement(statement, null);

        }
    }

    public void showUsers() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM pipe7709.Pirkejai");
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("No users were found");
                return;
            }

            System.out.println("PirkejoNr,vardas,pavarde,piniguKiekisPaskyroje");
            printResults(resultSet, 4);
        } catch (SQLException e) {
            System.out.println("SQL Error!");
            e.printStackTrace();
        } finally {
            closeStatement(statement, null);
        }
    }

    public void buyDrinkForAUser(int amount, int drinkNo, int userNr,
            int shopNo) {
        String updateString = "update pipe7709.Pirkejai set PiniguKiekisPaskyroje = PiniguKiekisPaskyroje - ? where Nr = ?";
        String updateStatement = "update pipe7709.ParduotuvesGerimas set Kiekis = Kiekis - ? where GerimoNr = ? AND ParduotuvesNr = ?";

        try (PreparedStatement updateSales = connection.prepareStatement(updateString);
                PreparedStatement updateTotal = connection.prepareStatement(updateStatement)) {

            PreparedStatement statement = connection
                    .prepareStatement("SELECT TiekejoKaina from pipe7709.KavaArbata WHERE pipe7709.KavaArbata.Nr = ?");
            statement.setInt(1, drinkNo);
            float cost = 0;
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("No drinks by id: " + drinkNo + " were found.");
                return;
            }

            cost = resultSet.getFloat(1);
            System.out.println(cost);

            connection.setAutoCommit(false);
            updateSales.setFloat(1, amount * cost);
            updateSales.setInt(2, userNr);
            updateSales.executeUpdate();

            updateTotal.setInt(1, amount);
            updateTotal.setInt(2, drinkNo);
            updateTotal.setInt(3, shopNo);
            updateTotal.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            rollbackTransaction(connection);
        }
    }

    private void printResults(ResultSet resultSet, int index) {
        try {
            do {
                for (int i = 0; i < index; i++) {
                    System.out.print(resultSet.getString(i + 1));
                    String symbol = i + 1 == index ? "\n" : ",";
                    System.out.print(symbol);
                }
            } while (resultSet.next());
        } catch (SQLException ex) {
            System.out.println("SQL Error!");
            ex.printStackTrace();
        }
    }

    private void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Couldn't find driver class!");
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private Connection getConnection() {
        SensitiveData data = new SensitiveData();
        Connection postGresConn = null;
        String user = data.userName;
        String password = data.password;
        try {
            postGresConn = DriverManager.getConnection("jdbc:postgresql://pgsql2.mif/studentu", user, password);
        } catch (SQLException ex) {
            System.out.println("Couldn't connect to database!");
            ex.printStackTrace();
            return null;
        }
        System.out.println("Successfully connected to Postgres Database");
        return postGresConn;
    }

    private void closeStatement(PreparedStatement statement, ResultSet resultSet) {
        try {
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException ex) {
            System.out.println("Unexpected SQL Error!");
            ex.printStackTrace();
        }
    }

    private void rollbackTransaction() {
        try {
            if (connection != null) {
                System.err.println("Transaction is being rolled back");
                connection.rollback();
            }
        } catch (SQLException ex) {
        }
    }
}