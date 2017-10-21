    public boolean canConvertFrom(Type otherType) {

        if (otherType.typeCode == Types.SQL_ALL_TYPES) {
            return true;
        }

        if (otherType.isNumberType()) {
            return true;
        }

        if (otherType.isIntervalType()) {
            return true;
        }

        if (otherType.isCharacterType()) {
            return true;
        }

        if (otherType.isBitType() && otherType.precision == 1) {
            return true;
        }

        return false;
    }
