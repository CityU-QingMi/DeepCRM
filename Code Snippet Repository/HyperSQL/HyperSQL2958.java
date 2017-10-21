    public boolean canConvertFrom(Type otherType) {

        if (otherType.typeCode == Types.SQL_ALL_TYPES) {
            return true;
        }

        if (otherType.isCharacterType()) {
            return true;
        }

        if (!otherType.isDateTimeType()) {
            return false;
        }

        if (otherType.typeCode == Types.SQL_DATE) {
            return typeCode != Types.SQL_TIME;
        } else if (otherType.typeCode == Types.SQL_TIME) {
            return typeCode != Types.SQL_DATE;
        }

        return true;
    }
