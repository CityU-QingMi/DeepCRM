    private void writeObject(final ObjectOutputStream out) throws IOException {
        getFormattedMessage();
        out.writeUTF(formattedMessage);
        out.writeUTF(messagePattern);
        final int length = parameters == null ? 0 : parameters.length;
        out.writeInt(length);
        serializedParameters = new String[length];
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                serializedParameters[i] = String.valueOf(parameters[i]);
                out.writeUTF(serializedParameters[i]);
            }
        }
    }
