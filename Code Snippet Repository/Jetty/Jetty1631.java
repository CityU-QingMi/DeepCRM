    public void addInputAndExecute(ByteBuffer in)
    {
        boolean fillable=false;
        try(Locker.Lock lock = _locker.lock())
        {
            if (_inQ.peek()==EOF)
                throw new RuntimeIOException(new EOFException());
            boolean was_empty=_inQ.isEmpty();
            if (in==null)
            {
                _inQ.add(EOF);
                fillable=true;
            }
            if (BufferUtil.hasContent(in))
            {
                _inQ.add(in);
                fillable=was_empty;
            }
        }
        if (fillable)
            execute(_runFillable);
    }
