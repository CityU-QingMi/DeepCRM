    private void transferRow(TransferResultSet r, PreparedStatement p,
                             int len,
                             int[] types)
                             throws DataAccessPointException, SQLException {

        for (int i = 1; i <= len; i++) {
            int    t = types[i];
            Object o = r.getObject(i);

            if (o == null) {
                if (p != null) {
                    p.setNull(i, t);
                }
            } else {
                o = helper.convertColumnValue(o, i, t);

                p.setObject(i, o);
            }
        }

        if (p != null) {
            p.execute();
        }
    }
