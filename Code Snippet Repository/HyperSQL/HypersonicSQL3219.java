        public ClientThreadProcedure(int number_of_txns, String url,
                                     String user, String password,
                                     int transactionMode) {

            System.out.println(number_of_txns);

            ntrans = number_of_txns;
            Conn   = connect(url, user, password);

            if (Conn == null) {
                return;
            }

            try {
                Conn.setAutoCommit(false);
                Conn.setTransactionIsolation(transactionMode);
                prepareStatements();
            } catch (Exception E) {
                System.out.println(E.getMessage());
                E.printStackTrace();
            }
        }
