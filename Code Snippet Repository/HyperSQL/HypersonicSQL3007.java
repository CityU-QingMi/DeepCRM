    protected Integer readInteger() {

        readNumberField(Type.SQL_INTEGER);

        if (value instanceof Long) {
            value = Type.SQL_INTEGER.convertToDefaultType(null, value);
        }

        return (Integer) value;
    }
