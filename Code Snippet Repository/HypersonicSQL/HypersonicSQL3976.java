    private void transferRow(TransferResultSet r) throws Exception {

        String sLast = "";
        int    len   = r.getColumnCount();

        if (WTextWrite == null) {
            try {
                WTextWrite = new BufferedWriter(new FileWriter(sFileName));
            } catch (IOException e) {
                throw new DataAccessPointException(e.getMessage());
            }
        }

        for (int i = 0; i < len; i++) {
            int t = r.getColumnType(i + 1);

            sLast = "column=" + r.getColumnName(i + 1) + " datatype="
                    + (String) helper.getSupportedTypes().get(new Integer(t));

            Object o = r.getObject(i + 1);

            if (o == null) {
                sLast += " value=<null>";
            } else {
                o     = helper.convertColumnValue(o, i + 1, t);
                sLast += " value=\'" + o.toString() + "\'";
            }

            WTextWrite.write("\t" + sLast + "\n");
            WTextWrite.flush();
        }

        WTextWrite.write("\n");
        WTextWrite.flush();

        sLast = "";
    }
