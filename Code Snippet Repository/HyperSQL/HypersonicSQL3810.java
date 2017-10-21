    public static Type getType(Type type, Collation collation) {

        if (type.getCollation() == collation) {
            return type;
        }

        if (type.isCharacterType()) {
            type                             = type.duplicate();
            ((CharacterType) type).collation = collation;
        }

        return type;
    }
