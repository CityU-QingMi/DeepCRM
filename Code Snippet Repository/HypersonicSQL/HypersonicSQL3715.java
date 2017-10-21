    public Object negate(Object a) {

        if (a == null) {
            return null;
        }

        if (a instanceof IntervalMonthData) {
            long units = ((IntervalMonthData) a).units;

            return new IntervalMonthData(-units, this);
        } else {
            long units = ((IntervalSecondData) a).units;
            int  nanos = ((IntervalSecondData) a).nanos;

            return new IntervalSecondData(-units, -nanos, this, true);
        }
    }
