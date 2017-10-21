        public void run() {

            Connection c = null;

            try {
                c = newConnection();

                final Statement statement = c.createStatement();

                for (int i = 0; i <= 16500; i++) {
                    statement.executeQuery("SELECT * FROM link_table");

                    synchronized (executeCount) {
                        executeCount++;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            } finally {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
