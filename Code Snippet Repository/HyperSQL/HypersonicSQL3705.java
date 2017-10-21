    public Object convertToTypeLimits(SessionInterface session, Object a) {

        if (a == null) {
            return null;
        }

        if (a instanceof IntervalMonthData) {
            IntervalMonthData im = (IntervalMonthData) a;

            if (im.units > getIntervalValueLimit()) {
                throw Error.error(ErrorCode.X_22015);
            }
        } else if (a instanceof IntervalSecondData) {
            IntervalSecondData is = (IntervalSecondData) a;

            if (is.units > getIntervalValueLimit()) {
                throw Error.error(ErrorCode.X_22015);
            }

//            int divisor = nanoScaleFactors[scale];
//            is.nanos = (is.nanos / divisor) * divisor;
        }

        return a;
    }
