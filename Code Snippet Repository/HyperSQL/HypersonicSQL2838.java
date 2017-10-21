    public void readAdditionalResults(SessionInterface session,
                                      DataInputStream inputStream,
                                      RowInputBinary in) throws IOException {

        Result currentResult = this;

        setSession(session);

        while (true) {
            int addedResultMode = inputStream.readByte();

            if (addedResultMode == ResultConstants.NONE) {
                return;
            }

            currentResult = newResult(null, inputStream, in, addedResultMode);

            addChainedResult(currentResult);
        }
    }
