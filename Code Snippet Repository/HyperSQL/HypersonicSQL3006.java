    protected void readFieldPrefix() {

        if (!noSeparators) {
            scanner.scanNext();

            if (statementType == ScriptReaderBase.DELETE_STATEMENT) {
                scanner.scanNext();
                scanner.scanNext();
            }
        }
    }
