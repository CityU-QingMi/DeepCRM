    @Override
    public boolean isOpen()
    {
        switch(_state.get())
        {
            case CLOSED:
                return false;
            default:
                return true;
        }
    }
