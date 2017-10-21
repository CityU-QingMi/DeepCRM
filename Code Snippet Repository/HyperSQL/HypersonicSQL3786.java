    public boolean canConvertFrom(Type otherType) {

        if (otherType.typeCode == typeCode) {
            return true;
        }

        if (otherType.typeCode == Types.SQL_ALL_TYPES) {
            return true;
        }

        return false;
    }
