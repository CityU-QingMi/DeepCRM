    public final static void checkType(final Object obj, final Class type)
        throws IllegalArgumentException {
        if (String.class.equals(type)) {
            coerceToString(obj);
        }
        if (ELArithmetic.isNumberType(type)) {
            coerceToNumber(obj, type);
        }
        if (Character.class.equals(type) || Character.TYPE == type) {
            coerceToCharacter(obj);
        }
        if (Boolean.class.equals(type) || Boolean.TYPE == type) {
            coerceToBoolean(obj);
        }
        if (type.isEnum()) {
            coerceToEnum(obj, type);
        }
    }
