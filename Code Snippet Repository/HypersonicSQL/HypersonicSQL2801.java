    static TextFileReader newTextFileReader(RandomAccessInterface dataFile,
            TextFileSettings textFileSettings, RowInputInterface rowIn,
            boolean isReadOnly) {

        if (textFileSettings.isUTF16) {
            return new TextFileReader16(dataFile, textFileSettings, rowIn,
                                        isReadOnly);
        } else {
            return new TextFileReader8(dataFile, textFileSettings, rowIn,
                                       isReadOnly);
        }
    }
