    boolean isEquivalent(Table mainTable, int[] mainCols, Table refTable,
                         int[] refCols) {

        switch (constType) {

            case SchemaObject.ConstraintTypes.MAIN :
            case SchemaObject.ConstraintTypes.FOREIGN_KEY :
                if (mainTable != core.mainTable || refTable != core.refTable) {
                    return false;
                }

                if (core.mainCols.length == mainCols.length
                        && core.refCols.length == refCols.length) {
                    return ArrayUtil.areEqualSets(core.mainCols, mainCols)
                           && ArrayUtil.areEqualSets(core.refCols, refCols);
                }
        }

        return false;
    }
