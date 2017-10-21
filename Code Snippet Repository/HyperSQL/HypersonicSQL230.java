    public Result getResult(Session session) {

        Object value = getValueInternal(session, null);

        if (value instanceof Result) {
            return (Result) value;
        }

        return Result.newPSMResult(value);
    }
