        public void run() {

            gResult.clear();

            try {
                if (txtCommand.getText().startsWith("-->>>TEST<<<--")) {
                    testPerformance();
                } else {
                    executeSQL();
                }

                updateResult();
                displayResults();
                updateAutoCommitBox();

                // System.gc();
            } catch (RuntimeException re) {
                CommonSwing.errorMessage(re);

                throw re;
            } finally {
                setWaiting(null);
            }
        }
