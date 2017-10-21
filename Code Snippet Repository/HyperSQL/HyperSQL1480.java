    public Object updateAggregatingValue(Session session, Object currValue) {

        if (!condition.testCondition(session)) {
            return currValue;
        }

        Object[] array = (Object[]) currValue;

        if (array == null) {
            array = new Object[3];
        }

        array[0] = Boolean.FALSE;

        getValueInternal(session, array);

        return array;
    }
