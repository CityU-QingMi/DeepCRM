    private void setResultColumnTypes() {

        resultColumnTypes = new Type[indexLimitData];

        for (int i = 0; i < indexLimitVisible; i++) {
            Expression e = exprColumns[i];

            resultColumnTypes[i] = e.getDataType();
        }

        for (int i = indexLimitVisible; i < indexLimitRowId; i++) {
            if (i == indexLimitVisible) {
                resultColumnTypes[i] = Type.SQL_BIGINT;
            } else {
                resultColumnTypes[i] = Type.SQL_ALL_TYPES;
            }
        }

        for (int i = indexLimitRowId; i < indexLimitData; i++) {
            Expression e    = exprColumns[i];
            Type       type = e.getDataType();

            if (type.getCollation() != e.collation && e.collation != null) {
                type = Type.getType(type, e.collation);
            }

            resultColumnTypes[i] = type;
        }
    }
