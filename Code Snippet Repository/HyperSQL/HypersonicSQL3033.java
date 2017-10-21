    protected void writeByteArray(byte[] b) {

        if (textFileSettings.isUTF16) {
            byte[] buffer = new byte[b.length * 2];

            StringConverter.writeHexBytes(buffer, 0, b);

            String s = new String(buffer);

            writeBytes(s);
        } else {
            ensureRoom(b.length * 2);
            StringConverter.writeHexBytes(this.getBuffer(), count, b);

            count += b.length * 2;
        }
    }
