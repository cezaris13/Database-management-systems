import java.sql.*;

@FunctionalInterface
interface ApplyStatementValues {
    void apply(PreparedStatement statement) throws SQLException;
}

enum Operation {
    Add,
    Search,
    Update,
    Remove
}

public class DatabaseCommands {

    public Connection connection;

    public DatabaseCommands() {
        loadDriver();
        this.connection = getConnection();
    }

    public void showProviders() {
        String sqlString = "SELECT * from pipe7709.Tiekejas";
        String emptyResultsString = "No providers found";
        String resultSetTitles = "TiekejoNr, Pavadinimas, TelefonoNumeris, ElPastas, Puslapis";

        executeSqlStatement(sqlString, emptyResultsString, resultSetTitles, Operation.Search);
    }

    public void showDrinks() {
        String sqlString = "SELECT * from pipe7709.KavaArbata";
        String emptyResultsString = "No drinks were found";
        String resultSetTitles = "GerimoNr, TiekejoNr, Pavadinimas, TiekejoKaina, PapildomaInformacija";

        executeSqlStatement(sqlString, emptyResultsString, resultSetTitles, Operation.Search);
    }

    public void showEquipment() {
        String sqlString = "SELECT * from pipe7709.Iranga";
        String emptyResultsString = "No equipment found";
        String resultSetTitles = "IrangosNr, TiekejoNr, Pavadinimas, TiekejoKaina, Talpa";
        executeSqlStatement(sqlString, emptyResultsString, resultSetTitles, Operation.Search);
    }

    public void showShops() {
        String sqlString = "SELECT * from pipe7709.Parduotuve";
        String emptyResultsString = "No shops found";
        String resultSetTitles = "GerimoNr, TiekejoNr, Pavadinimas, TiekejoKaina, PapildomaInformacija";

        executeSqlStatement(sqlString, emptyResultsString, resultSetTitles, Operation.Search);
    }

    public void showShopsEquipment() {
        String sqlString = "SELECT * from pipe7709.ParduotuvesIranga";
        String emptyResultsString = "No shops equipment were found";
        String resultSetTitles = "ParduotuvesNr, IrangosNr, Kiekis";

        executeSqlStatement(sqlString, emptyResultsString, resultSetTitles, Operation.Search);
    }

    public void showShopsDrinks() {
        String sqlString = "SELECT * from pipe7709.ParduotuvesGerimas";
        String emptyResultsString = "No shop drinks found";
        String resultSetTitles = "ParduotuvesNr, GerimoNr, Kiekis";

        executeSqlStatement(sqlString, emptyResultsString, resultSetTitles, Operation.Search);
    }

    public void findDrink(String drinkName) {
        String sqlString = "SELECT * from pipe7709.kavaArbata WHERE pipe7709.kavaArbata.Pavadinimas = ?";
        String emptyResultsString = "No drinks by the name: " + drinkName + " were found.";
        String resultSetTitles = "GerimoNr, TiekejoNr, Pavadinimas, TiekejoKaina, PapildomaInformacija";

        executeSqlStatement(sqlString, emptyResultsString, resultSetTitles, Operation.Search, statement -> {
            statement.setString(1, drinkName);
        });
    }

    public void showCoffeeTeaInTheShop(int shopId) {
        String sqlString = "SELECT KA.*, PG.Kiekis as GerimoKiekis " +
                "FROM pipe7709.KavaArbata as KA, pipe7709.ParduotuvesGerimas as PG, pipe7709.Parduotuve as P " +
                "WHERE P.Nr = ? AND P.Nr = PG.ParduotuvesNr AND PG.GerimoNr = KA.Nr";
        String emptyResultsString = "No shops,drinks or shop drinks were found";
        String resultSetTitles = "GerimoNr, TiekejoNr, Pavadinimas, TiekejoKaina, PapildomaInformacija,GerimoKiekis";

        executeSqlStatement(sqlString, emptyResultsString, resultSetTitles, Operation.Search, statement -> {
            statement.setInt(1, shopId);
        });
    }

    public void findEquipment(String equipmentName) {
        String sqlString = "SELECT * from pipe7709.Iranga WHERE pipe7709.Iranga.Pavadinimas = ?";
        String emptyResultsString = "No equipment found.";
        String resultSetTitles = "IrangosNr, TiekejoNr, Pavadinimas, TiekejoKaina, Talpa";

        executeSqlStatement(sqlString, emptyResultsString, resultSetTitles, Operation.Search, statement -> {
            statement.setString(1, equipmentName);
        });
    }

    public void addNewShop(String country, String city, String street, String bTime,
            String eTime) {
        String sqlString = "INSERT INTO pipe7709.Parduotuve (Salis,Miestas,Gatve,DarboPradzia,DarboPabaiga)" +
                " VALUES (?,?,?,?,?)";

        executeSqlStatement(sqlString, null, null, Operation.Add, statement -> {
            var startTime = Time.valueOf(bTime);
            var endTime = Time.valueOf(eTime);
            statement.setString(1, country);
            statement.setString(2, city);
            statement.setString(3, street);
            statement.setTime(4, startTime);
            statement.setTime(5, endTime);
        });
    }

    public void updateDrink(int id, String name) {
        String sqlString = "UPDATE pipe7709.kavaArbata" +
                " SET Pavadinimas = ? WHERE Nr = ?";

        executeSqlStatement(sqlString, null, null, Operation.Update, statement -> {
            statement.setString(1, name);
            statement.setInt(2, id);
        });
    }

    public void removeDrink(int id) {
        String sqlString = "DELETE FROM pipe7709.kavaArbata" +
                " WHERE pipe7709.kavaArbata.nr = ?";

        executeSqlStatement(sqlString, null, null, Operation.Remove, statement -> {
            statement.setInt(1, id);
        });
    }

    public void showUsers() {
        String sqlString = "SELECT * FROM pipe7709.Pirkejai";
        String emptyResultsString = "No users were found";
        String resultSetTitles = "PirkejoNr,vardas,pavarde,piniguKiekisPaskyroje";

        executeSqlStatement(sqlString, emptyResultsString, resultSetTitles, Operation.Search);
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

    private void printResults(ResultSet resultSet, String emptyResultsString, String resultSetTitles) {
        String[] tokens = resultSetTitles.split(",");
        int index = tokens.length;
        try {
            if (!resultSet.next()) {
                System.out.println(emptyResultsString);
                return;
            }

            System.out.println(resultSetTitles);
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

    private void rollbackTransaction(Connection connection) {
        try {
            if (connection != null) {
                System.err.println("Transaction is being rolled back");
                connection.rollback();
            }
        } catch (SQLException ex) {
        }
    }

    private void executeSqlStatement(String sqlString, String emptyResultsString, String resultSetTitles,
            Operation operation) {
        executeSqlStatement(sqlString, emptyResultsString, resultSetTitles, operation, statement -> {
        });
    }

    private void executeSqlStatement(String sqlString, String emptyResultsString, String resultSetTitles,
            Operation operation,
            ApplyStatementValues applyStatementValues) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sqlString);
            applyStatementValues.apply(statement);

            switch (operation) {
                case Add:
                    statement.execute();
                    break;
                case Update:
                case Remove:
                    statement.executeUpdate();
                    break;
                case Search:
                    resultSet = statement.executeQuery();
                    printResults(resultSet, emptyResultsString, resultSetTitles);
                    break;
                default:
                    break;
            }
        } catch (SQLException ex) {
            System.out.println("SQL Error!");
            ex.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("The given date(s) were invalid");
        } finally {
            closeStatement(statement, resultSet);
        }
    }
}