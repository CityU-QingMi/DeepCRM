    private void skipBOM() {

        try {
            if (textFileSettings.isUTF16) {
                dataFile.seek(0);

                if (dataFile.read() == 0xFE && dataFile.read() == 0xFF) {
                    position = 2;
                } else {
                    dataFile.seek(0);

                    if (dataFile.read() == 0xFF && dataFile.read() == 0xFE) {
                        position = 2;

                        textFileSettings.setLittleEndianByteOrderMark();
                    }
                }
            }
        } catch (IOException e) {
            throw Error.error(ErrorCode.TEXT_FILE_IO, e);
        }
    }
