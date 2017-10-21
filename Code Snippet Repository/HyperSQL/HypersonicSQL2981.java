    protected BinaryData readBit() {

        String s = readString();

        if (s == null) {
            return null;
        }

        BinaryData data = scanner.convertToBit(s);

        return data;
    }
