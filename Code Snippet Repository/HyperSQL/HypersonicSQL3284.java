    protected void doListing() {

        try {
            Connection con = getConnection("ABCD", "dcba", false);

            if (con != null) {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM MyTable ORDER BY Id ASC");

                append("Listing 'MyTable'....");

                while (rs.next()) {
                    append("  " + rs.getString(1) + ", " + rs.getString(2));
                }

                append("...done.");
                stmt.close();
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
