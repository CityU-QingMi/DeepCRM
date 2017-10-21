    public void checkFlush() throws IOException
    {
        State s=_state.get();
        switch(s)
        {
            case OSHUT:
            case OSHUTTING:
            case CLOSED:
                throw new IOException(s.toString());
            default:
                break;
        }
    }
