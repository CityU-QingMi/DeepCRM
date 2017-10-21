    private boolean completeRequest(Throwable failure)
    {
        if (requestState == State.PENDING)
        {
            requestState = State.COMPLETED;
            requestFailure = failure;
            return true;
        }
        return false;
    }
