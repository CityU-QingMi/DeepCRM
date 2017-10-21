    public Object getAggregatedValue(Session session, Object currValue) {

        Object[] array = (Object[]) currValue;

        if (array == null) {
            array = new Object[3];
        }

        array[0] = Boolean.TRUE;

        Result result = (Result) getValueInternal(session, array);
        Object returnValue;

        if (result.isError()) {
            throw result.getException();
        } else {
            return result.getValueObject();
        }
    }
