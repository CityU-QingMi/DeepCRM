    protected void initBuffers() {

        if (textFileSettings.isQuoted || textFileSettings.isAllQuoted) {
            rowIn  = new RowInputTextQuoted(textFileSettings);
            rowOut = new RowOutputTextQuoted(textFileSettings);
        } else {
            rowIn  = new RowInputText(textFileSettings);
            rowOut = new RowOutputText(textFileSettings);
        }
    }
