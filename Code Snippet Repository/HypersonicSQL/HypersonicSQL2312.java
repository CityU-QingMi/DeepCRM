    public static BigDecimal getBigDecimal(BigDecimal val) {

        if (val == null) {
            return val;
        }

        synchronized (bigdecimalPool) {
            return (BigDecimal) bigdecimalPool.getOrAddObject(val);
        }
    }
