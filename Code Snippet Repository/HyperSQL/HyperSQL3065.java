    private static BigDecimal convertToDecimal(Object a) {

        if (a instanceof BigDecimal) {
            return (BigDecimal) a;
        } else if (a instanceof Integer || a instanceof Long) {
            return BigDecimal.valueOf(((Number) a).longValue());
        } else if (a instanceof Double) {
            double value = ((Number) a).doubleValue();

            if (Double.isInfinite(value) || Double.isNaN(value)) {
                throw Error.error(ErrorCode.X_22003);
            }

            return BigDecimal.valueOf(value);
        } else {
            throw Error.runtimeError(ErrorCode.U_S0500, "NumberType");
        }
    }
