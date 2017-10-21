    static private OdbcPacketInputStream newOdbcPacketInputStream(char cType,
            InputStream streamSource,
            Integer packetSizeObj) throws IOException {

        int bytesRead, i;
        int packetSize = 0;

        if (packetSizeObj == null) {
            byte[] fourBytes = new byte[4];

            bytesRead = 0;

            while ((i =
                    streamSource.read(fourBytes, bytesRead, fourBytes.length
                                      - bytesRead)) > 0) {
                bytesRead += i;
            }

            if (bytesRead != fourBytes.length) {
                throw new EOFException("Failed to read size header int");
            }

            packetSize = ((fourBytes[0] & 0xff) << 24)
                         + ((fourBytes[1] & 0xff) << 16)
                         + ((fourBytes[2] & 0xff) << 8)
                         + (fourBytes[3] & 0xff) - 4;

            // Minus 4 because this counts the size int itself.
        } else {
            packetSize = packetSizeObj.intValue();
        }

        byte[] xferBuffer = new byte[packetSize];

        bytesRead = 0;

        while ((i = streamSource.read(xferBuffer, bytesRead, xferBuffer.length
                                      - bytesRead)) > 0) {
            bytesRead += i;
        }

        if (bytesRead != xferBuffer.length) {
            throw new EOFException(
                "Failed to read packet contents from given stream");
        }

        return new OdbcPacketInputStream(cType,
                                         new ByteArrayInputStream(xferBuffer));
    }
