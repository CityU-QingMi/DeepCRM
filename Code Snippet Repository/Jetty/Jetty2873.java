    public boolean isAsync()
    {
        switch (_state.get())
        {
            case ASYNC:
            case READY:
            case PENDING:
            case UNREADY:
                return true;
            default:
                return false;
        }
    }
