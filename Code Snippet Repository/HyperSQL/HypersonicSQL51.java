    boolean isUniqueWithColumns(int[] cols) {

        switch (constType) {

            case SchemaObject.ConstraintTypes.PRIMARY_KEY :
            case SchemaObject.ConstraintTypes.UNIQUE :
                if (core.mainCols.length == cols.length) {
                    return ArrayUtil.haveEqualSets(core.mainCols, cols,
                                                   cols.length);
                }
        }

        return false;
    }
