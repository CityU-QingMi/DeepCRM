    protected boolean isInRange(Number value, String stringValue, Class toType) {
        Number bigValue = null;
        Number lowerBound = null;
        Number upperBound = null;

        try {
            if (double.class == toType || Double.class == toType) {
                bigValue = new BigDecimal(stringValue);
                // Double.MIN_VALUE is the smallest positive non-zero number
                lowerBound = BigDecimal.valueOf(Double.MAX_VALUE).negate();
                upperBound = BigDecimal.valueOf(Double.MAX_VALUE);
            } else if (float.class == toType || Float.class == toType) {
                bigValue = new BigDecimal(stringValue);
                // Float.MIN_VALUE is the smallest positive non-zero number
                lowerBound = BigDecimal.valueOf(Float.MAX_VALUE).negate();
                upperBound = BigDecimal.valueOf(Float.MAX_VALUE);
            } else if (byte.class == toType || Byte.class == toType) {
                bigValue = new BigInteger(stringValue);
                lowerBound = BigInteger.valueOf(Byte.MIN_VALUE);
                upperBound = BigInteger.valueOf(Byte.MAX_VALUE);
            } else if (char.class == toType || Character.class == toType) {
                bigValue = new BigInteger(stringValue);
                lowerBound = BigInteger.valueOf(Character.MIN_VALUE);
                upperBound = BigInteger.valueOf(Character.MAX_VALUE);
            } else if (short.class == toType || Short.class == toType) {
                bigValue = new BigInteger(stringValue);
                lowerBound = BigInteger.valueOf(Short.MIN_VALUE);
                upperBound = BigInteger.valueOf(Short.MAX_VALUE);
            } else if (int.class == toType || Integer.class == toType) {
                bigValue = new BigInteger(stringValue);
                lowerBound = BigInteger.valueOf(Integer.MIN_VALUE);
                upperBound = BigInteger.valueOf(Integer.MAX_VALUE);
            } else if (long.class == toType || Long.class == toType) {
                bigValue = new BigInteger(stringValue);
                lowerBound = BigInteger.valueOf(Long.MIN_VALUE);
                upperBound = BigInteger.valueOf(Long.MAX_VALUE);
            } else {
                throw new IllegalArgumentException("Unexpected numeric type: " + toType.getName());
            }
        } catch (NumberFormatException e) {
            //shoult it fail here? BigInteger doesnt seem to be so nice parsing numbers as NumberFormat
            return true;
        }

        return ((Comparable) bigValue).compareTo(lowerBound) >= 0 && ((Comparable) bigValue).compareTo(upperBound) <= 0;
    }
