    private void skipBOM() {

        try {
            if (textFileSettings.isUTF8) {
                dataFile.seek(0);

                if (dataFile.read() == 0xEF && dataFile.read() == 0xBB
                        && dataFile.read() == 0xBF) {
                    position = 3;
                }
            }
        } catch (IOException e) {
            throw Error.error(ErrorCode.TEXT_FILE_IO, e);
        }
    }
