    public String getDatabaseHandle(Connection con) throws SQLException {
        
        String productName = con.getMetaData().getDatabaseProductName();
        String handle = "mysql";
        if (       productName.toLowerCase().contains("mysql")) {
            handle =  "mysql";
        } else if (productName.toLowerCase().contains("derby")) {
            handle =  "derby";
        } else if (productName.toLowerCase().contains("hsql")) {
            handle =  "hsqldb";
        } else if (productName.toLowerCase().contains("postgres")) {
            handle =  "postgresql";
        } else if (productName.toLowerCase().contains("oracle")) {
            handle =  "oracle";
        } else if (productName.toLowerCase().contains("microsoft")) {
            handle =  "mssql";
        } else if (productName.toLowerCase().contains("db2")) {
            handle =  "db2";
        }
        
        return handle;
    }
