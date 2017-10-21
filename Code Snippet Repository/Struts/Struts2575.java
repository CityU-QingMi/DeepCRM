    public final static Number coerceToNumber(final Object obj, final Class type)
            throws IllegalArgumentException {
        if (obj == null || "".equals(obj)) {
            return coerceToNumber(ZERO, type);
        }
        if (obj instanceof String) {
            return coerceToNumber((String) obj, type);
        }
        if (ELArithmetic.isNumber(obj)) {
            return coerceToNumber((Number) obj, type);
        }

        if (obj instanceof Character) {
            return coerceToNumber(new Short((short) ((Character) obj)
                    .charValue()), type);
        }

        throw new IllegalArgumentException(MessageFactory.get("error.convert",
                obj, obj.getClass(), type));
    }
