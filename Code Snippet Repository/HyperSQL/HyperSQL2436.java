        public ClientThread(int number_of_txns, String url, String user,
                            String password, int transactionMode) {

            System.out.println(number_of_txns);

            ntrans = number_of_txns;
            Conn   = connect(url, user, password);

            if (Conn == null) {
                return;
            }

            try {
                if (transactions) {
                    Conn.setAutoCommit(false);
                }

                Conn.setTransactionIsolation(transactionMode);

                if (prepared_stmt) {
                    String Query;

                    Query  = "UPDATE accounts ";
                    Query  += "SET     Abalance = Abalance + ? ";
                    Query  += "WHERE   Aid = ?";
                    pstmt1 = Conn.prepareStatement(Query);
                    Query  = "SELECT Abalance ";
                    Query  += "FROM   accounts ";
                    Query  += "WHERE  Aid = ?";
                    pstmt2 = Conn.prepareStatement(Query);
                    Query  = "UPDATE tellers ";
                    Query  += "SET    Tbalance = Tbalance + ? ";
                    Query  += "WHERE  Tid = ?";
                    pstmt3 = Conn.prepareStatement(Query);
                    Query  = "UPDATE branches ";
                    Query  += "SET    Bbalance = Bbalance + ? ";
                    Query  += "WHERE  Bid = ?";
                    pstmt4 = Conn.prepareStatement(Query);
                    Query  = "INSERT INTO history(Tid, Bid, Aid, delta) ";
                    Query  += "VALUES (?,?,?,?)";
                    pstmt5 = Conn.prepareStatement(Query);
                }
            } catch (Exception E) {
                System.out.println(E.toString());
                E.printStackTrace();
            }
        }
