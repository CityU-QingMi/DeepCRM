    public Connection getConnection() throws SQLException {
        Context ctx = null;
        try {
            Object ds = this.datasource;

            if (ds == null || isAlwaysLookup()) {
                ctx = (props != null) ? new InitialContext(props): new InitialContext(); 

                ds = ctx.lookup(url);
                if (!isAlwaysLookup()) {
                    this.datasource = ds;
                }
            }

            if (ds == null) {
                throw new SQLException( "There is no object at the JNDI URL '" + url + "'");
            }

            if (ds instanceof XADataSource) {
                return (((XADataSource) ds).getXAConnection().getConnection());
            } else if (ds instanceof DataSource) { 
                return ((DataSource) ds).getConnection();
            } else {
                throw new SQLException("Object at JNDI URL '" + url + "' is not a DataSource.");
            }
        } catch (Exception e) {
            this.datasource = null;
            throw new SQLException(
                    "Could not retrieve datasource via JNDI url '" + url + "' "
                            + e.getClass().getName() + ": " + e.getMessage());
        } finally {
            if (ctx != null) {
                try { ctx.close(); } catch(Exception ignore) {}
            }
        }
    }
