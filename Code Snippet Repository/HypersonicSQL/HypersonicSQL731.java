    void scanBinaryString() {

        byteOutputStream.reset(byteBuffer);

        while (true) {
            scanBinaryStringPart();

            if (token.isMalformed) {
                return;
            }

            if (scanSeparator() && charAt(currentPosition) == '\'') {
                continue;
            }

            break;
        }

        token.tokenValue = new BinaryData(byteOutputStream.toByteArray(),
                                          false);

        byteOutputStream.reset(byteBuffer);
    }
