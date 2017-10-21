        void prepareStatements() throws SQLException {

            String Query;

            Query =
                "UPDATE accounts SET Abalance = Abalance + ? WHERE  Aid = ?";
            pstmt1 = Conn.prepareStatement(Query);
            Query  = "SELECT Abalance FROM   accounts WHERE  Aid = ?";
            pstmt2 = Conn.prepareStatement(Query);
            Query =
                "UPDATE tellers SET Tbalance = Tbalance + ? WHERE  Tid = ?";
            pstmt3 = Conn.prepareStatement(Query);
            Query =
                "UPDATE branches SET Bbalance = Bbalance + ? WHERE  Bid = ?";
            pstmt4 = Conn.prepareStatement(Query);
            Query =
                "INSERT INTO history(Tid, Bid, Aid, delta) VALUES (?,?,?,?)";
            pstmt5 = Conn.prepareStatement(Query);
        }
