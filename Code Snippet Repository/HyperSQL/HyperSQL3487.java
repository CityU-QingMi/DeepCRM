    public void setReturningResult() {

        if (compileContext.getSequences().length > 0) {
            throw Error.error(ErrorCode.X_42598);
        }

        isTopLevel = true;

        setReturningResultSet();
    }
