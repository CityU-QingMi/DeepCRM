    public Object absolute(Object a) {

        if (a == null) {
            return null;
        }

        if (a instanceof IntervalMonthData) {
            if (((IntervalMonthData) a).units < 0) {
                return negate(a);
            }
        } else {
            if (((IntervalSecondData) a).units < 0
                    || ((IntervalSecondData) a).nanos < 0) {
                return negate(a);
            }
        }

        return a;
    }
