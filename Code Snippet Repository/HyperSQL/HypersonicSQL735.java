    void scanBitString() {

        BitMap map = new BitMap(0, true);

        while (true) {
            scanBitStringPart(map);

            if (token.isMalformed) {
                return;
            }

            if (scanSeparator() && charAt(currentPosition) == '\'') {
                continue;
            }

            break;
        }

        token.tokenValue = BinaryData.getBitData(map.getBytes(), map.size());
    }
