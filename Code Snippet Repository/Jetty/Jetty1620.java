    @Override
    public boolean isInputShutdown()
    {
        switch(_state.get())
        {
            case CLOSED:
            case ISHUT:
            case ISHUTTING:
                return true;
            default:
                return false;
        }
    }
