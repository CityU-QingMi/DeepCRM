    public final static Number toNumber(final String value) {
        try {
            return new Integer(Integer.parseInt(value));
        } catch (NumberFormatException e0) {
            try {
                return new Long(Long.parseLong(value));
            } catch (NumberFormatException e1) {
                return new BigInteger(value);
            }
        }
    }
