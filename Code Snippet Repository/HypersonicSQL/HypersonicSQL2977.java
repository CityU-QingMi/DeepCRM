    protected IntervalMonthData readYearMonthInterval(Type type) {

        String s = readString();

        if (s == null) {
            return null;
        }

        s = s.trim();

        if (s.length() == 0) {
            return null;
        }

        return (IntervalMonthData) scanner.newInterval(s, (IntervalType) type);
    }
