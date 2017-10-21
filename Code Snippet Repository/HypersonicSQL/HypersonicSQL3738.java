    public Type getAggregateType(Type other) {

        if (other == null) {
            return this;
        }

        if (other == SQL_ALL_TYPES) {
            return this;
        }

        if (typeCode == other.typeCode) {
            if (precision >= other.precision && scale >= other.scale) {
                return this;
            } else if (precision <= other.precision && scale <= other.scale) {
                return other;
            }
        }

        if (other.isCharacterType()) {
            return other.getAggregateType(this);
        }

        if (!other.isIntervalType()) {
            throw Error.error(ErrorCode.X_42562);
        }

        int startType = ((IntervalType) other).startIntervalType
                        > startIntervalType ? startIntervalType
                                            : ((IntervalType) other)
                                                .startIntervalType;
        int endType = ((IntervalType) other).endIntervalType > endIntervalType
                      ? ((IntervalType) other).endIntervalType
                      : endIntervalType;
        int  newType      = getCombinedIntervalType(startType, endType);
        long newPrecision = precision > other.precision ? precision
                                                        : other.precision;
        int  newScale     = scale > other.scale ? scale
                                                : other.scale;

        try {
            return getIntervalType(newType, startType, endType, newPrecision,
                                   newScale, false);
        } catch (RuntimeException e) {
            throw Error.error(ErrorCode.X_42562);
        }
    }
