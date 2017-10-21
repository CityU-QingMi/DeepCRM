    public Object updateAggregatingValue(Session session, Object currValue) {

        if (!nodes[RIGHT].testCondition(session)) {
            return currValue;
        }

        if (currValue == null) {
            currValue = new SetFunction(session, opType, nodes[LEFT].dataType,
                                        dataType, isDistinctAggregate,
                                        arrayType);
        }

        Object newValue = nodes[LEFT].opType == OpTypes.ASTERISK
                          ? ValuePool.INTEGER_1
                          : nodes[LEFT].getValue(session);

        ((SetFunction) currValue).add(session, newValue);

        return currValue;
    }
