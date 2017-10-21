    public static boolean isValidDatetimeRange(Type a, Type b) {

        if (!a.isDateTimeType()) {
            return false;
        }

        if (b.isDateTimeType()) {
            if ((a.typeCode == Types.SQL_TIME && b.typeCode == Types.SQL_DATE)
                    || (a.typeCode == Types.SQL_DATE
                        && b.typeCode == Types.SQL_TIME)) {
                return false;
            }

            return true;
        }

        if (b.isIntervalType()) {
            return ((DateTimeType) a).canAdd((IntervalType) b);
        }

        return false;
    }
