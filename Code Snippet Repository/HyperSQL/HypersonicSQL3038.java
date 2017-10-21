    protected void writeBinary(BinaryData o) {

        ensureRoom((int) (o.length(null) * 2 + 2));
        write('\'');

        count += StringConverter.writeHexBytes(getBuffer(), count,
                                               o.getBytes());

        write('\'');
    }
