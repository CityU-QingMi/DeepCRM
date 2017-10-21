    @Override
    public boolean isOutputShutdown()
    {
        switch(_state.get())
        {
            case CLOSED:
            case OSHUT:
            case OSHUTTING:
                return true;
            default:
                return false;
        }
    }
