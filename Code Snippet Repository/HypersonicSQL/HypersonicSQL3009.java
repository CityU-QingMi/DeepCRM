    protected Double readReal() {

        readNumberField(Type.SQL_DOUBLE);

        if (value == null) {
            return null;
        }

        if (scanner.scanSpecialIdentifier(Tokens.T_DIVIDE_OP)) {
            scanner.scanNext();

            Object divisor = scanner.getValue();
            double i       = ((Number) divisor).doubleValue();

            if (i == 0) {
                if (((Number) value).doubleValue() == 1E0) {
                    i = Double.NEGATIVE_INFINITY;
                } else if (((Number) value).doubleValue() == -1E0) {
                    i = Double.POSITIVE_INFINITY;
                } else if (((Number) value).doubleValue() == 0E0) {
                    i = Double.NaN;
                } else {
                    throw Error.error(ErrorCode.X_42585);
                }
            } else {
                throw Error.error(ErrorCode.X_42585);
            }

            value = Double.valueOf(i);
        }

        return (Double) value;
    }
