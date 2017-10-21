        int doOne(int bid, int tid, int aid, int delta) {

            int aBalance = 0;

            if (Conn == null) {
                incrementFailedTransactionCount();

                return 0;
            }

            try {
                pstmt1.setInt(1, aid);
                pstmt1.setInt(2, tid);
                pstmt1.setInt(3, bid);
                pstmt1.setInt(4, delta);
                pstmt1.execute();
                pstmt1.getUpdateCount();

                if (pstmt1.getMoreResults()) {
                    ResultSet rs = pstmt1.getResultSet();

                    while (rs.next()) {
                        aBalance = rs.getInt(1);
                    }

                    rs.close();
                }

                pstmt1.clearWarnings();
                Conn.commit();

                return aBalance;
            } catch (Exception E) {
                if (verbose) {
                    System.out.println("Transaction failed: "
                                       + E.getMessage());
                    E.printStackTrace();
                }

                incrementFailedTransactionCount();

                try {
                    Conn.rollback();
                } catch (SQLException E1) {}
            }

            return 0;
        }    /* end of DoOne         */
