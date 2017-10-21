    static TransferHelper getHelper(String productLowerName) {

        TransferHelper f = null;

        if (productLowerName.indexOf("hsql database") != -1) {
            f = new HsqldbTransferHelper();
        } else if (productLowerName.indexOf("postgresql") != -1) {
            f = new PostgresTransferHelper();
        } else if (productLowerName.indexOf("mckoi") != -1) {
            f = new McKoiTransferHelper();
        } else if (productLowerName.indexOf("informix") != -1) {
            f = new InformixTransferHelper();
        } else if (productLowerName.indexOf("oracle") != -1) {
            System.out.println("using the Oracle helper");

            f = new OracleTransferHelper();
        } else if (productLowerName.equals("access")
                   || (productLowerName.indexOf("microsoft") != -1)) {
            f = new SqlServerTransferHelper();
        } else {
            f = new TransferHelper();
        }

        return (f);
    }
