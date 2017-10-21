    public IntervalMonthData(long months, IntervalType type) {

        if (months >= type.getIntervalValueLimit()) {
            throw Error.error(ErrorCode.X_22006);
        }

        if (type.typeCode == Types.SQL_INTERVAL_YEAR) {
            months -= (months % 12);
        }

        this.units = (int) months;
    }
