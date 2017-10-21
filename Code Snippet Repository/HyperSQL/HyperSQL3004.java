    public IntervalSecondData(long seconds, long nanos, IntervalType type,
                              boolean normalise) {

        if (nanos >= DTIType.limitNanoseconds) {
            long carry = nanos / DTIType.limitNanoseconds;

            nanos   = nanos % DTIType.limitNanoseconds;
            seconds += carry;
        } else if (nanos <= -DTIType.limitNanoseconds) {
            long carry = -nanos / DTIType.limitNanoseconds;

            nanos   = -(-nanos % DTIType.limitNanoseconds);
            seconds -= carry;
        }

        int scaleFactor = DTIType.nanoScaleFactors[type.scale];

        nanos /= scaleFactor;
        nanos *= scaleFactor;

        if (seconds > 0 && nanos < 0) {
            nanos += DTIType.limitNanoseconds;

            seconds--;
        } else if (seconds < 0 && nanos > 0) {
            nanos -= DTIType.limitNanoseconds;

            seconds++;
        }

        scaleFactor = DTIType.yearToSecondFactors[type.endPartIndex];
        seconds     /= scaleFactor;
        seconds     *= scaleFactor;

        if (seconds >= type.getIntervalValueLimit()) {
            throw Error.error(ErrorCode.X_22015);
        }

        this.units = seconds;
        this.nanos = (int) nanos;
    }
