    private void createInitialReader() throws IOException, JasperException {

        // wrap this stream in RewindableInputStream
        stream = new RewindableInputStream(stream);

        // perform auto-detect of encoding if necessary
        if (encoding == null) {
            // read first four bytes and determine encoding
            final byte[] b4 = new byte[4];
            int count = 0;
            for (; count < 4; count++) {
                b4[count] = (byte) stream.read();
            }
            if (count == 4) {
                Object[] encodingDesc = getEncodingName(b4, count);
                encoding = (String) (encodingDesc[0]);
                isBigEndian = (Boolean) (encodingDesc[1]);

                if (encodingDesc.length > 3) {
                    isBomPresent = (Boolean) (encodingDesc[2]);
                    skip = (Integer) (encodingDesc[3]);
                } else {
                    isBomPresent = true;
                    skip = (Integer) (encodingDesc[2]);
                }

                stream.reset();
                // Special case UTF-8 files with BOM created by Microsoft
                // tools. It's more efficient to consume the BOM than make
                // the reader perform extra checks. -Ac
                if (count > 2 && encoding.equals("UTF-8")) {
                    int b0 = b4[0] & 0xFF;
                    int b1 = b4[1] & 0xFF;
                    int b2 = b4[2] & 0xFF;
                    if (b0 == 0xEF && b1 == 0xBB && b2 == 0xBF) {
                        // ignore first three bytes...
                        stream.skip(3);
                    }
                }
                reader = createReader(stream, encoding, isBigEndian);
            } else {
                reader = createReader(stream, encoding, isBigEndian);
            }
        }
    }
