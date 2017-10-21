    public static IntervalType getIntervalType(int startIndex, int endIndex,
            long precision, int fractionPrecision) {

        boolean defaultPrecision = precision == -1;

        if (startIndex == -1 || endIndex == -1) {
            throw Error.error(ErrorCode.X_22006);
        }

        if (startIndex > endIndex) {
            throw Error.error(ErrorCode.X_22006);
        }

        if (startIndex <= DTIType.INTERVAL_MONTH_INDEX
                && endIndex > DTIType.INTERVAL_MONTH_INDEX) {
            throw Error.error(ErrorCode.X_22006);
        }

        int startType = DTIType.intervalParts[startIndex];
        int endType   = DTIType.intervalParts[endIndex];
        int type      = DTIType.intervalTypes[startIndex][endIndex];

        if (precision == 0
                || fractionPrecision > DTIType.maxFractionPrecision) {
            throw Error.error(ErrorCode.X_42592);
        }

        if (startIndex == DTIType.INTERVAL_SECOND_INDEX) {
            if (precision > DTIType.maxIntervalSecondPrecision) {
                throw Error.error(ErrorCode.X_42592);
            }
        } else if (precision > DTIType.maxIntervalPrecision) {
            throw Error.error(ErrorCode.X_42592);
        }

        if (precision == -1) {
            precision = DTIType.defaultIntervalPrecision;
        }

        if (fractionPrecision == -1) {
            fractionPrecision = endType == Types.SQL_INTERVAL_SECOND
                                ? DTIType.defaultIntervalFractionPrecision
                                : 0;
        }

        return getIntervalType(type, startType, endType, precision,
                               fractionPrecision, defaultPrecision);
    }
