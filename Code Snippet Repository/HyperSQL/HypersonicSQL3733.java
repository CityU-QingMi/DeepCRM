    public Object convertFromDouble(double value) {

        long units = (long) value;

        if (this.isIntervalYearMonthType()) {
            return new IntervalMonthData(units);
        } else {
            int nanos = (int) ((value - units) * nanoScaleFactors[0]);

            return new IntervalSecondData(units, nanos);
        }
    }
