    protected final static Number coerceToNumber(final Number number,
            final Class type) throws IllegalArgumentException {
        if (Long.TYPE == type || Long.class.equals(type)) {
            return new Long(number.longValue());
        }
        if (Double.TYPE == type || Double.class.equals(type)) {
            return new Double(number.doubleValue());
        }
        if (Integer.TYPE == type || Integer.class.equals(type)) {
            return new Integer(number.intValue());
        }
        if (BigInteger.class.equals(type)) {
            if (number instanceof BigDecimal) {
                return ((BigDecimal) number).toBigInteger();
            }
            if (number instanceof BigInteger) {
                return number;
            }
            return BigInteger.valueOf(number.longValue());
        }
        if (BigDecimal.class.equals(type)) {
            if (number instanceof BigDecimal) {
                return number;
            }
            if (number instanceof BigInteger) {
                return new BigDecimal((BigInteger) number);
            }
            return new BigDecimal(number.doubleValue());
        }
        if (Byte.TYPE == type || Byte.class.equals(type)) {
            return new Byte(number.byteValue());
        }
        if (Short.TYPE == type || Short.class.equals(type)) {
            return new Short(number.shortValue());
        }
        if (Float.TYPE == type || Float.class.equals(type)) {
            return new Float(number.floatValue());
        }

        throw new IllegalArgumentException(MessageFactory.get("error.convert",
                number, number.getClass(), type));
    }
