    public static boolean funcTest2(Connection conn,
                                    String value)
                                    throws java.sql.SQLException {

        if (value != null && value.startsWith("te")) {
            return true;
        }

        return false;
    }
