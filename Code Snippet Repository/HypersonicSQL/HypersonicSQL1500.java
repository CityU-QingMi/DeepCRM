    public Reader getCharacterStream(long pos,
                                     long length) throws SQLException {

        if (length > Integer.MAX_VALUE) {
            throw JDBCUtil.outOfRangeArgument("length: " + length);
        }

        final String data = getData();
        final int dlen = data.length();

        if (pos == MIN_POS && length == dlen) {
            return new StringReader(data);
        }

        if (pos < MIN_POS || pos > dlen) {
            throw JDBCUtil.outOfRangeArgument("pos: " + pos);
        }

        final long startIndex = pos - 1;

        if (length < 0 || length > dlen - startIndex) {
            throw JDBCUtil.outOfRangeArgument("length: " + length);
        }

        final int endIndex = (int) (startIndex + length); // exclusive
        final char[] chars = new char[(int)length];

        data.getChars((int)startIndex, endIndex, chars, 0);

        return new CharArrayReader(chars);
    }
