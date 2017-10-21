    protected void writeUUID(BinaryData o) {

        ensureRoom(40);
        write('\'');

        count += StringConverter.writeUUIDHexBytes(getBuffer(), count,
                o.getBytes());

        write('\'');
    }
