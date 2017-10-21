    boolean resolvesDuplicateColumnReference(RangeVariable rangeVar) {

        if (tableName == null) {
            Expression e = rangeVar.getColumnExpression(columnName);

            if (e != null) {
                return false;
            }
        }

        switch (rangeVar.rangeType) {

            case RangeVariable.PARAMETER_RANGE :
            case RangeVariable.VARIALBE_RANGE :
            case RangeVariable.TRANSITION_RANGE :
                return false;

            default :
                int colIndex = rangeVar.findColumn(schema, tableName,
                                                   columnName);

                return colIndex != -1;
        }
    }
