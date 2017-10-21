    protected final Number coerce(final Object obj) {

        if (isNumber(obj)) {
            return coerce((Number) obj);
        }
        if (obj instanceof String) {
            return coerce((String) obj);
        }
        if (obj == null || "".equals(obj)) {
            return coerce(ZERO);
        }

        if (obj instanceof Character) {
            return coerce(new Short((short) ((Character) obj).charValue()));
        }

        throw new IllegalArgumentException(MessageFactory.get("error.convert",
                obj, obj.getClass(), "Number"));
    }
