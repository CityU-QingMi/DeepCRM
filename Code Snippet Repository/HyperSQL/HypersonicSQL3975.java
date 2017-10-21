    void putData(String statement, TransferResultSet r,
                 int iMaxRows) throws DataAccessPointException {

        int i = 0;

        if (r == null) {
            return;
        }

        if (WTextWrite == null) {
            try {
                WTextWrite = new BufferedWriter(new FileWriter(sFileName));
            } catch (IOException e) {
                throw new DataAccessPointException(e.getMessage());
            }
        }

        try {
            while (r.next()) {
                if (i == 0) {
                    WTextWrite.write(statement + "\n");
                    WTextWrite.flush();
                }

                transferRow(r);

                if (iMaxRows != 0 && i == iMaxRows) {
                    break;
                }

                i++;

                if (iMaxRows != 0 || i % 100 == 0) {
                    tracer.trace("Transfered " + i + " rows");
                }
            }
        } catch (Exception e) {
            throw new DataAccessPointException(e.getMessage());
        } finally {
            try {
                if (i > 0) {
                    WTextWrite.write("\tNumber of Rows=" + i + "\n\n");
                    WTextWrite.flush();
                }
            } catch (IOException e) {
                throw new DataAccessPointException(e.getMessage());
            }
        }
    }
