        public void run() {

            while (ntrans-- > 0) {
                int account = JDBCBench.getRandomID(ACCOUNT);
                int branch  = JDBCBench.getRandomID(BRANCH);
                int teller  = JDBCBench.getRandomID(TELLER);
                int delta   = JDBCBench.getRandomInt(0, 1000);

                doOne(branch, teller, account, delta);
                incrementTransactionCount();
            }

            if (prepared_stmt) {
                try {
                    if (pstmt1 != null) {
                        pstmt1.close();
                    }

                    if (pstmt2 != null) {
                        pstmt2.close();
                    }

                    if (pstmt3 != null) {
                        pstmt3.close();
                    }

                    if (pstmt4 != null) {
                        pstmt4.close();
                    }

                    if (pstmt5 != null) {
                        pstmt5.close();
                    }
                } catch (Exception E) {
                    System.out.println(E.toString());
                    E.printStackTrace();
                }
            }

            connectClose(Conn);

            Conn = null;
        }
