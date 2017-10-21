    public Object updateAggregatingValue(Session session, Object currValue) {

        if (!condition.testCondition(session)) {
            return currValue;
        }

        Object currentVal = null;

        switch (opType) {

            case OpTypes.ARRAY_AGG :
            case OpTypes.GROUP_CONCAT :
                Object[] row = new Object[nodes.length];

                for (int i = 0; i < nodes.length; i++) {
                    row[i] = nodes[i].getValue(session);
                }

                if (opType == OpTypes.GROUP_CONCAT
                        && row[row.length - 1] == null) {
                    return currValue;
                }

                currentVal = row;
                break;

            case OpTypes.MEDIAN :
                currentVal = nodes[0].getValue(session);

                if (currentVal == null) {
                    return currValue;
                }
                break;
        }

        HsqlArrayList list = (HsqlArrayList) currValue;

        if (list == null) {
            list = new HsqlArrayList();
        }

        list.add(currentVal);

        return list;
    }
