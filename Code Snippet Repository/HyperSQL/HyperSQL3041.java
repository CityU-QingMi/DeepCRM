    public String getDefinition() {

        if (precision == defaultIntervalPrecision
                && (endIntervalType != Types.SQL_INTERVAL_SECOND
                    || scale == defaultIntervalFractionPrecision)) {
            return getNameString();
        }

        StringBuffer sb = new StringBuffer(32);

        sb.append(Tokens.T_INTERVAL).append(' ');
        sb.append(getQualifier(startIntervalType));

        if (typeCode == Types.SQL_INTERVAL_SECOND) {
            sb.append('(');
            sb.append(precision);

            if (scale != defaultIntervalFractionPrecision) {
                sb.append(',');
                sb.append(scale);
            }

            sb.append(')');

            return sb.toString();
        }

        if (precision != defaultIntervalPrecision) {
            sb.append('(');
            sb.append(precision);
            sb.append(')');
        }

        if (startIntervalType != endIntervalType) {
            sb.append(' ');
            sb.append(Tokens.T_TO);
            sb.append(' ');
            sb.append(Tokens.SQL_INTERVAL_FIELD_NAMES[endPartIndex]);

            if (endIntervalType == Types.SQL_INTERVAL_SECOND
                    && scale != defaultIntervalFractionPrecision) {
                sb.append('(');
                sb.append(scale);
                sb.append(')');
            }
        }

        return sb.toString();
    }
