    public static void connectClose(Connection c) {

        if (c == null) {
            return;
        }

        try {
            c.close();
        } catch (Exception E) {
            System.out.println(E.toString());
            E.printStackTrace();
        }
    }
