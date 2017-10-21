    public void readLobResults(SessionInterface session,
                               DataInputStream inputStream,
                               RowInputBinary in) throws IOException {

        Result  currentResult = this;
        boolean hasLob        = false;

        setSession(session);

        while (true) {
            int addedResultMode = inputStream.readByte();

            if (addedResultMode == ResultConstants.LARGE_OBJECT_OP) {
                ResultLob resultLob = ResultLob.newLob(inputStream, false);

                if (session instanceof Session) {
                    ((Session) session).allocateResultLob(resultLob,
                                                          inputStream);
                } else {
                    currentResult.addLobResult(resultLob);
                }

                hasLob = true;

                continue;
            } else if (addedResultMode == ResultConstants.NONE) {
                break;
            } else {
                throw Error.runtimeError(ErrorCode.U_S0500, "Result");
            }
        }

        if (hasLob) {
            ((Session) session).registerResultLobs(currentResult);
        }
    }
