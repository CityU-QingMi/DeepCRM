    public Object getValue(Session session) {

        Object returnValue = getValueInternal(session, null);

        if (returnValue instanceof Result) {
            Result result = (Result) returnValue;

            if (result.isError()) {
                throw result.getException();
            } else if (result.isSimpleValue()) {
                returnValue = result.getValueObject();
            } else if (result.isData()) {
                returnValue = result;
            } else {
                throw Error.error(ErrorCode.X_2F005, routine.getName().name);
            }
        }

        return returnValue;
    }
