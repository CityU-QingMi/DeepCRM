    public Object round(Object a, int s) {

        if (a == null) {
            return null;
        }

        BigDecimal dec = convertToDecimal(a);

        switch (typeCode) {

            case Types.SQL_DOUBLE : {
                dec = dec.setScale(s, BigDecimal.ROUND_HALF_EVEN);

                break;
            }
            case Types.SQL_DECIMAL :
            case Types.SQL_NUMERIC :
            default : {
                dec = dec.setScale(s, BigDecimal.ROUND_HALF_UP);
                dec = dec.setScale(scale, BigDecimal.ROUND_DOWN);

                break;
            }
        }

        a = convertToDefaultType(null, dec);

        return convertToTypeLimits(null, a);
    }
