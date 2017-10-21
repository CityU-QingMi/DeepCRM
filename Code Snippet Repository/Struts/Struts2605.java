    public Object getValue(EvaluationContext ctx)
            throws ELException {
        Object obj = this.children[0].getValue(ctx);

        if (obj == null) {
            return new Long(0);
        }
        if (obj instanceof BigDecimal) {
            return ((BigDecimal) obj).negate();
        }
        if (obj instanceof BigInteger) {
            return ((BigInteger) obj).negate();
        }
        if (obj instanceof String) {
            if (isStringFloat((String) obj)) {
                return new Double(-Double.parseDouble((String) obj));
            }
            return new Long(-Long.parseLong((String) obj));
        }
        if (obj instanceof Long) {
            return new Long(-((Long) obj).longValue());
        }
        if (obj instanceof Double) {
            return new Double(-((Double) obj).doubleValue());
        }
        if (obj instanceof Integer) {
            return new Integer(-((Integer) obj).intValue());
        }
        if (obj instanceof Float) {
            return new Float(-((Float) obj).floatValue());
        }
        if (obj instanceof Short) {
            return new Short((short) -((Short) obj).shortValue());
        }
        if (obj instanceof Byte) {
            return new Byte((byte) -((Byte) obj).byteValue());
        }
        Long num = (Long) coerceToNumber(obj, Long.class);
        return new Long(-num.longValue());
    }
