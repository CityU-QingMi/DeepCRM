    String intervalMonthToString(Object a) {

        StringBuffer sb     = new StringBuffer(8);
        long         months = ((IntervalMonthData) a).units;

        if (months < 0) {
            months = -months;

            sb.append('-');
        }

        for (int i = startPartIndex; i <= endPartIndex; i++) {
            int  factor = DTIType.yearToSecondFactors[i];
            long part   = months / factor;

            if (i == startPartIndex) {
                int zeros = (int) precision - getPrecisionExponent(part);
/**/
/**/
/**/
/**/
/**/
            } else if (part < 10) {
                sb.append('0');
            }

            sb.append(part);

            months %= factor;

            if (i < endPartIndex) {
                sb.append((char) DTIType.yearToSecondSeparators[i]);
            }
        }

        return sb.toString();
    }
