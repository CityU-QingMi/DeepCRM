    private static void doUpdateInsertDeleteWaehler(Connection p_connection)
    throws SQLException {

        System.out.println("UPDATE WAEHLER START ...");

        PreparedStatement p = p_connection.prepareStatement(
            "UPDATE WAEHLER SET AUSTRITTSDATUM=? WHERE NAME=?");

        p.setDate(1, null);
        p.setString(2, "Muster1");
        p.execute();
        p.close();
        System.out.println("END UPDATE WAEHLER");
        System.out.println("INSERT INTO WAEHLER START ...");

        p = p_connection.prepareStatement(
            "INSERT INTO WAEHLER (NAME, AUSTRITTSDATUM) VALUES (?,?)");

        Calendar cal = GregorianCalendar.getInstance();

        p.setString(1, "Muster3");
        p.setDate(2, new Date(cal.getTimeInMillis()), cal);
        p.execute();
        p.close();
        System.out.println("END INSERT INTO WAEHLER");
        System.out.println("DELETE FROM WAEHLER START ...");

        p = p_connection.prepareStatement(
            "DELETE FROM WAEHLER WHERE NAME = ?");

        p.setString(1, "Muster2");
        p.execute();
        p.close();
        System.out.println("END DELETE FROM WAEHLER");
    }
