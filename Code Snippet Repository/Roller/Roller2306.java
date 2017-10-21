    public String getDatabaseProductName() {
        String name = "unknown";

        Connection con = null;
        try {
            con = WebloggerStartup.getDatabaseProvider().getConnection();
            name = con.getMetaData().getDatabaseProductName();
        } catch (Exception intentionallyIgnored) {
            // ignored
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ex) {
                }
            }
        }

        return name;
    }
