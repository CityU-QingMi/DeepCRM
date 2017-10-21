    public boolean isNegative(Object a) {

        if (a instanceof IntervalMonthData) {
            return ((IntervalMonthData) a).units < 0;
        } else {
            long units = ((IntervalSecondData) a).units;

            if (units < 0) {
                return true;
            } else if (units == 0) {
                return ((IntervalSecondData) a).nanos < 0;
            } else {
                return false;
            }
        }
    }
