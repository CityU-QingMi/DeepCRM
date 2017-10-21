    String fixupColumnDefRead(TransferTable t, ResultSetMetaData meta,
                              String columnType, ResultSet columnDesc,
                              int columnIndex) throws SQLException {

        String SeqName   = new String("_" + columnDesc.getString(4) + "_seq");
        int    spaceleft = 31 - SeqName.length();

        if (t.Stmts.sDestTable.length() > spaceleft) {
            SeqName = t.Stmts.sDestTable.substring(0, spaceleft) + SeqName;
        } else {
            SeqName = t.Stmts.sDestTable + SeqName;
        }

        String CompareString = "nextval(\'\"" + SeqName + "\"\'";

        if (columnType.indexOf(CompareString) >= 0) {

            // We just found a increment
            columnType = "SERIAL";
        }

        for (int Idx = 0; Idx < Funcs.length; Idx++) {
            String PostgreSQL_func = Funcs[Idx][PostgreSQL];
            int    iStartPos       = columnType.indexOf(PostgreSQL_func);

            if (iStartPos >= 0) {
                String NewColumnType = columnType.substring(0, iStartPos);

                NewColumnType += Funcs[Idx][HSQLDB];
                NewColumnType +=
                    columnType.substring(iStartPos
                                         + PostgreSQL_func.length());
                columnType = NewColumnType;
            }
        }

        return (columnType);
    }
