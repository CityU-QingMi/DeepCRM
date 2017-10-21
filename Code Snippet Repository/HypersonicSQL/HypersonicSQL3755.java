    private static Double convertToDouble(Object a) {

        if (a instanceof java.lang.Double) {
            return (Double) a;
        }

        double value = toDouble(a);

        return ValuePool.getDouble(Double.doubleToLongBits(value));
    }
