    public void addChainedResult(Result result) {

        Result current = this;

        while (current.chainedResult != null) {
            current = current.chainedResult;
        }

        current.chainedResult = result;
    }
