    protected BinaryData readUUID() {

        String s = readString();

        if (s == null) {
            return null;
        }

        BinaryData data = scanner.convertToBinary(s, true);

        return data;
    }
