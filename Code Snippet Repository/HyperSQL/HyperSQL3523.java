    private void setResultNullability() {

        for (int i = 0; i < indexLimitVisible; i++) {
            Expression e           = exprColumns[i];
            byte       nullability = e.getNullability();

            if (e.opType == OpTypes.COLUMN) {
                RangeVariable range = e.getRangeVariable();

                if (range != null) {
                    if (range.rangePositionInJoin >= startInnerRange
                            && range.rangePositionInJoin < endInnerRange) {

                        //
                    } else {
                        nullability = SchemaObject.Nullability.NULLABLE;
                    }
                }
            }

            resultMetaData.columns[i].setNullability(nullability);
        }
    }
