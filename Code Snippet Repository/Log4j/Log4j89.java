    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        formattedMessage = in.readUTF();
        key = in.readUTF();
        baseName = in.readUTF();
        in.readInt();
        stringArgs = (String[]) in.readObject();
        logger = StatusLogger.getLogger();
        resourceBundle = null;
        argArray = null;
    }
