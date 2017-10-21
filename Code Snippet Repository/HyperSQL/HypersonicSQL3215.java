    public static Connection connect(String DBUrl, String DBUser,
                                     String DBPassword) {

        try {
            Connection conn = DriverManager.getConnection(DBUrl, DBUser,
                DBPassword);

            return conn;
        } catch (Exception E) {
            System.out.println(E.getMessage());
            E.printStackTrace();
        }

        return null;
    }
