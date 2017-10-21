    @Override
    public String toString()
    {
        State state;
        long consumed;
        int q;
        Content content;
        synchronized (_inputQ)
        {
            state = _state;
            consumed = _contentConsumed;
            q = _inputQ.size();
            content = _inputQ.peekFirst();
        }
        return String.format("%s@%x[c=%d,q=%d,[0]=%s,s=%s]",
            getClass().getSimpleName(),
            hashCode(),
            consumed,
            q,
            content,
            state);
    }
