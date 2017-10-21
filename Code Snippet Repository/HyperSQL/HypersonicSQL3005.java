    protected void readNumberField(Type type) {

        readFieldPrefix();
        scanner.scanNext();

        boolean minus = scanner.getTokenType() == Tokens.MINUS_OP;

        if (minus) {
            scanner.scanNext();
        }

        value = scanner.getValue();

        if (minus) {
            try {
                value = scanner.getDataType().negate(value);
            } catch (HsqlException e) {}
        }
    }
