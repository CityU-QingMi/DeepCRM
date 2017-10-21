        public void run() {

            int count = ntrans;

            while (count-- > 0) {
                int account = TestBench.getRandomID(ACCOUNT);
                int branch  = account / naccounts;
                int teller  = TestBench.getRandomID(TELLER);
                int delta   = TestBench.getRandomInt(-1000, 1000);

                doOne(branch, teller, account, delta);
                incrementTransactionCount();
            }

            try {
                if (pstmt1 != null) {
                    pstmt1.close();
                }
            } catch (Exception E) {
                System.out.println(E.getMessage());
                E.printStackTrace();
            }

            connectClose(Conn);

            Conn = null;
        }
