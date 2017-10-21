    public final static Character coerceToCharacter(final Object obj)
            throws IllegalArgumentException {
        if (obj == null || "".equals(obj)) {
            return new Character((char) 0);
        }
        if (obj instanceof String) {
            return new Character(((String) obj).charAt(0));
        }
        if (ELArithmetic.isNumber(obj)) {
            return new Character((char) ((Number) obj).shortValue());
        }
        Class objType = obj.getClass();
        if (obj instanceof Character) {
            return (Character) obj;
        }

        throw new IllegalArgumentException(MessageFactory.get("error.convert",
                obj, objType, Character.class));
    }
