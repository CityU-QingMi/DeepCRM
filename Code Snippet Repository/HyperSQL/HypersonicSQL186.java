    public boolean isIndexable(RangeVariable rangeVar) {

        boolean result;

        switch (opType) {

            case OpTypes.AND : {
                result = nodes[LEFT].isIndexable(rangeVar)
                         || nodes[RIGHT].isIndexable(rangeVar);

                return result;
            }
            case OpTypes.OR : {
                result = nodes[LEFT].isIndexable(rangeVar)
                         && nodes[RIGHT].isIndexable(rangeVar);

                return result;
            }
            default : {
                Expression temp = getIndexableExpression(rangeVar);

                return temp != null;
            }
        }
    }
