    protected void writeOther(JavaObjectData o) {

        ensureRoom(o.getBytesLength() * 2 + 2);
        write('\'');
        StringConverter.writeHexBytes(getBuffer(), count, o.getBytes());

        count += o.getBytesLength() * 2;

        write('\'');
    }
