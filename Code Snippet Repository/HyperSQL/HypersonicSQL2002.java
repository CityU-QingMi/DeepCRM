    public String readUTF() throws IOException {

        int bytecount = readUnsignedShort();

        if (pos + bytecount >= count) {
            throw new EOFException();
        }

        String result = StringConverter.readUTF(buffer, pos, bytecount);

        pos += bytecount;

        return result;
    }
