    void formatResultSet(ResultSet r) {

        if (r == null) {
            String[] g = new String[1];

            g[0] = "Result";

            gResult.setHead(g);

            g[0] = "(empty)";

            gResult.addRow(g);

            return;
        }

        try {
            ResultSetMetaData m   = r.getMetaData();
            int               col = m.getColumnCount();
            String[]          h   = new String[col];

            for (int i = 1; i <= col; i++) {
                h[i - 1] = m.getColumnLabel(i);
            }

            gResult.setHead(h);

            while (r.next()) {
                for (int i = 1; i <= col; i++) {
                    try {
                        h[i - 1] = r.getString(i);

                        if (r.wasNull()) {
                            h[i - 1] = "(null)";
                        }
                    } catch (SQLException e) {
                        h[i - 1] = "(binary data)";
                    }
                }

                gResult.addRow(h);
            }

            r.close();
        } catch (SQLException e) {}
    }
