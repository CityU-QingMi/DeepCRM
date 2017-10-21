    public static void translateDefaultDatabaseProperty(HsqlProperties p) {

        if (p == null) {
            return;
        }

        if (!p.isPropertyTrue(ServerProperties.sc_key_remote_open_db)) {
            if (p.getProperty(ServerProperties.sc_key_database + "." + 0)
                    == null) {
                String defaultdb =
                    p.getProperty(ServerProperties.sc_key_database);

                if (defaultdb == null) {
                    defaultdb = SC_DEFAULT_DATABASE;
                } else {
                    p.removeProperty(ServerProperties.sc_key_database);
                }

                p.setProperty(ServerProperties.sc_key_database + ".0",
                              defaultdb);
                p.setProperty(ServerProperties.sc_key_dbname + ".0", "");
            }

            if (p.getProperty(ServerProperties.sc_key_dbname + "." + 0)
                    == null) {
                p.setProperty(ServerProperties.sc_key_dbname + ".0", "");
            }
        }
    }
