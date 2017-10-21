    static long testStatement(Statement sStatement, String sql,
                              int max) throws SQLException {

        long start = System.currentTimeMillis();

        if (sql.indexOf('#') == -1) {
            max = 1;
        }

        for (int i = 0; i < max; i++) {
            String s = sql;

            while (true) {
                int j = s.indexOf("#r#");

                if (j == -1) {
                    break;
                }

                s = s.substring(0, j) + ((int) (Math.random() * i))
                    + s.substring(j + 3);
            }

            while (true) {
                int j = s.indexOf('#');

                if (j == -1) {
                    break;
                }

                s = s.substring(0, j) + i + s.substring(j + 1);
            }

            sStatement.execute(s);
        }

        return (System.currentTimeMillis() - start);
    }
