    protected void checkState()
    {
        if (!isUtf8SequenceComplete())
        {
            _codep=0;
            _state = UTF8_ACCEPT;
            try
            {
                _appendable.append(REPLACEMENT);
            }
            catch(IOException e)
            {
                throw new RuntimeException(e);
            }
            throw new NotUtf8Exception("incomplete UTF8 sequence");
        }
    }
