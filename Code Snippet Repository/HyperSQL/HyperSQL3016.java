    public int compareToTypeRange(Object o) {

        long max = precisionLimits[(int) precision];
        long units;

        if (o instanceof IntervalMonthData) {
            units = ((IntervalMonthData) o).units;
        } else if (o instanceof IntervalSecondData) {
            units = ((IntervalSecondData) o).units;
        } else {
            return 0;
        }

        if (units >= max) {
            return 1;
        }

        if (units < 0) {
            if (-units >= max) {
                return -1;
            }
        }

        return 0;
    }
