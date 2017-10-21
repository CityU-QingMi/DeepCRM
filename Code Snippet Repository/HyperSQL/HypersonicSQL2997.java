    protected IntervalSecondData readDaySecondInterval(Type type) {

        readField();

        if (value == null) {
            return null;
        }

        return (IntervalSecondData) scanner.newInterval((String) value,
                (IntervalType) type);
    }
