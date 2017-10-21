    boolean hasColumnOnly(int colIndex) {

        switch (constType) {

            case SchemaObject.ConstraintTypes.CHECK :
                return rangeVariable.usedColumns[colIndex] && ArrayUtil
                    .countTrueElements(rangeVariable.usedColumns) == 1;

            case SchemaObject.ConstraintTypes.PRIMARY_KEY :
            case SchemaObject.ConstraintTypes.UNIQUE :
                return core.mainCols.length == 1
                       && core.mainCols[0] == colIndex;

            case SchemaObject.ConstraintTypes.MAIN :
                return false;

            case SchemaObject.ConstraintTypes.FOREIGN_KEY :
                return core.refCols.length == 1 && core.refCols[0] == colIndex;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "Constraint");
        }
    }
