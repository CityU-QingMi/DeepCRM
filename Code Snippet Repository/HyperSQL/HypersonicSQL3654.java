    static int getPrecisionExponent(long value) {

        int i = 1;

        for (; i < precisionLimits.length; i++) {
            if (value < precisionLimits[i]) {
                break;
            }
        }

        return i;
    }
