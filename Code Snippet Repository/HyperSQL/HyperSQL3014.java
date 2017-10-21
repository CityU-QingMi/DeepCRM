    public boolean canConvertFrom(Type otherType) {

        if (otherType.typeCode == Types.SQL_ALL_TYPES) {
            return true;
        }

        if (otherType.isCharacterType()) {
            return true;
        }

        if (otherType.isNumberType()) {
            return true;
        }

        if (!otherType.isIntervalType()) {
            return false;
        }

        return isIntervalYearMonthType()
               == otherType.isIntervalYearMonthType();
    }
