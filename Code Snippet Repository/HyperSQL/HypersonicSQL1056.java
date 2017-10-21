    int[] getUniqueNotNullColumnGroup(boolean[] usedColumns) {

        for (int i = 0, count = constraintList.length; i < count; i++) {
            Constraint constraint = constraintList[i];

            if (constraint.getConstraintType()
                    == SchemaObject.ConstraintTypes.UNIQUE) {
                int[] indexCols = constraint.getMainColumns();

                if (ArrayUtil.areAllIntIndexesInBooleanArray(
                        indexCols, colNotNull) && ArrayUtil
                            .areAllIntIndexesInBooleanArray(
                                indexCols, usedColumns)) {
                    return indexCols;
                }
            } else if (constraint.getConstraintType()
                       == SchemaObject.ConstraintTypes.PRIMARY_KEY) {
                int[] indexCols = constraint.getMainColumns();

                if (ArrayUtil.areAllIntIndexesInBooleanArray(indexCols,
                        usedColumns)) {
                    return indexCols;
                }
            }
        }

        return null;
    }
