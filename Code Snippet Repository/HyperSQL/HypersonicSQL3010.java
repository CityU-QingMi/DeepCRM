    protected BigDecimal readDecimal(Type type) {

        readNumberField(type);

        if (value == null) {
            return null;
        }

        BigDecimal bd = (BigDecimal) type.convertToDefaultType(null, value);

        return bd;
    }
