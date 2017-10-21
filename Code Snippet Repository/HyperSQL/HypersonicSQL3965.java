    void putData(String statement, TransferResultSet r,
                 int iMaxRows) throws DataAccessPointException {

        if ((statement == null) || statement.equals("") || (r == null)) {
            return;
        }

        PreparedStatement destPrep = null;

        try {
            destPrep = conn.prepareStatement(statement);

            int   i = 0;
            int   tmpLength;
            int   len      = r.getColumnCount();
            int[] tmpTypes = null;

            while (r.next()) {
                if (tmpTypes == null) {
                    tmpTypes = new int[len + 1];

                    for (int j = 1; j <= len; j++) {
                        tmpTypes[j] = r.getColumnType(j);
                    }
                }

                transferRow(r, destPrep, len, tmpTypes);

                if (iMaxRows != 0 && i == iMaxRows) {
                    break;
                }

                i++;

                if (iMaxRows != 0 || i % 100 == 0) {
                    tracer.trace("Transfered " + i + " rows");
                }
            }
        } catch (SQLException e) {
            throw new DataAccessPointException(e.toString());
        } finally {
            if (destPrep != null) {
                try {
                    destPrep.close();
                } catch (SQLException e) {}
            }
        }
    }
