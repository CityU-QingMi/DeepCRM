    public Object truncate(Object a, int s) {

        if (a == null) {
            return null;
        }

        BigDecimal dec = convertToDecimal(a);

        dec = dec.setScale(s, BigDecimal.ROUND_DOWN);

        if (typeCode == Types.SQL_DECIMAL || typeCode == Types.SQL_NUMERIC) {
            dec = dec.setScale(scale, BigDecimal.ROUND_DOWN);
        }

        a = convertToDefaultType(null, dec);

        return convertToTypeLimits(null, a);
    }
