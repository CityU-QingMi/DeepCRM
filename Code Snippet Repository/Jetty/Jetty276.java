    private boolean completeResponse(Throwable failure)
    {
        if (responseState == State.PENDING)
        {
            responseState = State.COMPLETED;
            responseFailure = failure;
            return true;
        }
        return false;
    }
