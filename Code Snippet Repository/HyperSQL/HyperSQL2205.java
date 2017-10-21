    protected BinaryData readBinary() {

        String s = readString();

        if (s == null) {
            return null;
        }

        BinaryData data = scanner.convertToBinary(s, false);

        return data;
    }
