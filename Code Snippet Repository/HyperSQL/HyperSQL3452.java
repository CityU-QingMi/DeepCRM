    boolean hasColumnPlus(int colIndex) {

        switch (constType) {

            case SchemaObject.ConstraintTypes.CHECK :
                return rangeVariable.usedColumns[colIndex] && ArrayUtil
                    .countTrueElements(rangeVariable.usedColumns) > 1;

            case SchemaObject.ConstraintTypes.PRIMARY_KEY :
            case SchemaObject.ConstraintTypes.UNIQUE :
                return core.mainCols.length != 1
                       && ArrayUtil.find(core.mainCols, colIndex) != -1;

            case SchemaObject.ConstraintTypes.MAIN :
                return ArrayUtil.find(core.mainCols, colIndex) != -1;

            case SchemaObject.ConstraintTypes.FOREIGN_KEY :
                return core.refCols.length != 1
                       && ArrayUtil.find(core.refCols, colIndex) != -1;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "Constraint");
        }
    }
