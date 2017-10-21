    boolean hasColumn(int colIndex) {

        switch (constType) {

            case SchemaObject.ConstraintTypes.CHECK :
                return rangeVariable.usedColumns[colIndex];

            case SchemaObject.ConstraintTypes.PRIMARY_KEY :
            case SchemaObject.ConstraintTypes.UNIQUE :
            case SchemaObject.ConstraintTypes.MAIN :
                return ArrayUtil.find(core.mainCols, colIndex) != -1;

            case SchemaObject.ConstraintTypes.FOREIGN_KEY :
                return ArrayUtil.find(core.refCols, colIndex) != -1;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "Constraint");
        }
    }
