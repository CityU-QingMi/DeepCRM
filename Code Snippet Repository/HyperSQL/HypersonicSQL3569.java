    public String convertToString(Object a) {

        if (a == null) {
            return null;
        }

        return StringConverter.byteArrayToBitString(
            ((BinaryData) a).getBytes(),
            (int) ((BinaryData) a).bitLength(null));
    }
