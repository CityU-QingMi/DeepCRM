    public synchronized BinaryData convertToBit(String s) {

        BitMap map      = new BitMap(0, true);
        int    bitIndex = 0;

        reset(s);
        resetState();
        byteOutputStream.reset(byteBuffer);

        for (; currentPosition < limit; currentPosition++) {
            int c = sqlString.charAt(currentPosition);

            if (c == '0') {
                map.unset(bitIndex);

                bitIndex++;
            } else if (c == '1') {
                map.set(bitIndex);

                bitIndex++;
            } else {
                token.tokenType   = Tokens.X_MALFORMED_BIT_STRING;
                token.isMalformed = true;

                throw Error.error(ErrorCode.X_22018);
            }
        }

        map.setSize(bitIndex);

        return BinaryData.getBitData(map.getBytes(), map.size());
    }
