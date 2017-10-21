    protected Object readOther() {

        String s = readString();

        if (s == null) {
            return null;
        }

        BinaryData data = scanner.convertToBinary(s, false);

        if (data.length(null) == 0) {
            return null;
        }

        return new JavaObjectData(data.getBytes());
    }
