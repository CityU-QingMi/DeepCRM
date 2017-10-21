    String fixupColumnDefRead(String aTableName, ResultSetMetaData meta,
                              String columnType, ResultSet columnDesc,
                              int columnIndex) throws SQLException {

        String SeqName   = new String("_" + columnDesc.getString(4) + "_seq");
        int    spaceleft = 31 - SeqName.length();

        if (aTableName.length() > spaceleft) {
            SeqName = aTableName.substring(0, spaceleft) + SeqName;
        } else {
            SeqName = aTableName + SeqName;
        }

        String CompareString = "nextval(\'\"" + SeqName + "\"\'";

        if (columnType.indexOf(CompareString) >= 0) {

            // We just found a increment
            columnType = "SERIAL";
        }

        for (int Idx = 0; Idx < Funcs.length; Idx++) {
            String ORACLE_func = Funcs[Idx][ORACLE];
            int    iStartPos   = columnType.indexOf(ORACLE_func);

            if (iStartPos >= 0) {
                String NewColumnType = columnType.substring(0, iStartPos);

                NewColumnType += Funcs[Idx][HSQLDB];
                NewColumnType += columnType.substring(iStartPos
                                                      + ORACLE_func.length());
                columnType = NewColumnType;
            }
        }

        return (columnType);
    }
