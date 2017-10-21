    public void checkFill() throws IOException
    {
        State s=_state.get();
        switch(s)
        {
            case ISHUT:
            case ISHUTTING:
            case CLOSED:
                throw new IOException(s.toString());
            default:
                break;
        }
    }
