    protected Long readBigint() {

        readNumberField(Type.SQL_BIGINT);

        if (value == null) {
            return null;
        }

        if (value instanceof BigDecimal) {
            return (Long) Type.SQL_BIGINT.convertToDefaultType(null, value);
        }

        return ValuePool.getLong(((Number) value).longValue());
    }
