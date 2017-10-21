    private void readObject(final ObjectInputStream in) throws IOException {
        parameters = null;
        throwable = null;
        formattedMessage = in.readUTF();
        messagePattern = in.readUTF();
        final int length = in.readInt();
        serializedParameters = new String[length];
        for (int i = 0; i < length; ++i) {
            serializedParameters[i] = in.readUTF();
        }
    }
