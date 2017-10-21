    public String convertToSQLString(Object a) {

        if (a == null) {
            return Tokens.T_NULL;
        }

        return StringConverter.byteArrayToSQLBitString(
            ((BinaryData) a).getBytes(),
            (int) ((BinaryData) a).bitLength(null));
    }
