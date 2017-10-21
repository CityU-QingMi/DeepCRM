    int readChar() {

        try {
            int c1 = dataFile.read();

            return c1;
        } catch (IOException e) {
            throw Error.error(ErrorCode.TEXT_FILE_IO, e);
        }
    }
