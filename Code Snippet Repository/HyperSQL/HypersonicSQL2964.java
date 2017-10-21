    public String readString() {

        try {
            int length = readInt();

            if (length < 0) {
                throw Error.error(ErrorCode.GENERAL_IO_ERROR,
                                  "RowInputBinary - negative length");
            }

            String s = StringConverter.readUTF(buffer, pos, length);

            s   = ValuePool.getString(s);
            pos += length;

            return s;
        } catch (IOException e) {
            throw Error.error(e, ErrorCode.GENERAL_IO_ERROR,
                              "RowInputBinary" + ' ' + getFilePosition());
        }
    }
