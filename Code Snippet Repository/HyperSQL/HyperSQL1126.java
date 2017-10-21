    public void writeUTF(String str) throws IOException {

        int len = str.length();

        if (len > 0xffff) {
            throw new UTFDataFormatException();
        }

        ensureRoom(len * 3 + 2);

        //
        int initpos = count;

        count += 2;

        StringConverter.stringToUTFBytes(str, this);

        int bytecount = count - initpos - 2;

        if (bytecount > 0xffff) {
            count = initpos;

            throw new UTFDataFormatException();
        }

        buffer[initpos++] = (byte) (bytecount >>> 8);
        buffer[initpos]   = (byte) bytecount;
    }
