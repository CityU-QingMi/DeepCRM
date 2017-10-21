    public void writeUTF(String str) throws IOException {

        int len = str.length();

        if (len > 0xffff) {
            throw new UTFDataFormatException();
        }

        int bytecount = StringConverter.getUTFSize(str);

        if (bytecount > 0xffff) {
            throw new UTFDataFormatException();
        }

        //
        writeChar(bytecount);

        HsqlByteArrayOutputStream bao =
            new HsqlByteArrayOutputStream(bytecount);

        StringConverter.stringToUTFBytes(str, bao);
        this.write(bao.getBuffer(), 0, bao.size());
    }
