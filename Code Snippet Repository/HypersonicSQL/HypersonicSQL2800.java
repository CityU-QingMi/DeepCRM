    int readChar() {

        try {
            int c1 = dataFile.read();

            if (c1 == -1) {
                return -1;
            }

            int c2 = dataFile.read();

            if (c2 == -1) {
                return -1;
            }

            if (textFileSettings.isLittleEndian) {
                int temp = c1;

                c1 = c2;
                c2 = temp;
            }

            return (char) ((c1 << 8) + c2);
        } catch (IOException e) {
            throw Error.error(ErrorCode.TEXT_FILE_IO, e);
        }
    }
