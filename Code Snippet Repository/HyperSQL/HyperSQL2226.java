    protected BinaryData readBit() {

        readFieldPrefix();

        if (scanner.scanNull()) {
            return null;
        }

        scanner.scanBitStringWithQuote();

        if (scanner.getTokenType() == Tokens.X_MALFORMED_BIT_STRING) {
            throw Error.error(ErrorCode.X_42587);
        }

        value = scanner.getValue();

        return (BinaryData) value;
    }
