    public boolean isInProgress()
    {
        switch(_state.get().getType())
        {
            case WRITING:
            case PENDING:
            case COMPLETING:
                return true;
            default:
                return false;
        }
    }
