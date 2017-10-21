    public double convertToDouble(Object interval) {

        if (this.isIntervalYearMonthType()) {
            double months = ((IntervalMonthData) interval).units;

            return months;
        } else {
            double seconds = ((IntervalSecondData) interval).units;

            seconds += ((double) ((IntervalSecondData) interval).nanos)
                       / nanoScaleFactors[0];

            return seconds;
        }
    }
