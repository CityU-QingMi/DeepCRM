    protected IntervalMonthData readYearMonthInterval(Type type) {

        readField();

        if (value == null) {
            return null;
        }

        return (IntervalMonthData) scanner.newInterval((String) value,
                (IntervalType) type);
    }
