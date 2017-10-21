    public static Type getCombinedIntervalType(IntervalType type1,
            IntervalType type2) {

        int startType = type2.startIntervalType > type1.startIntervalType
                        ? type1.startIntervalType
                        : type2.startIntervalType;
        int endType = type2.endIntervalType > type1.endIntervalType
                      ? type2.endIntervalType
                      : type1.endIntervalType;
        int  type              = getCombinedIntervalType(startType, endType);
        long precision = type1.precision > type2.precision ? type1.precision
                                                           : type2.precision;
        int  fractionPrecision = type1.scale > type2.scale ? type1.scale
                                                           : type2.scale;

        return getIntervalType(type, startType, endType, precision,
                               fractionPrecision, false);
    }
