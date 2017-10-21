    public String toReplacedString()
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
            Throwable th= new NotUtf8Exception("incomplete UTF8 sequence");
            LOG.warn(th.toString());
            LOG.debug(th);
        }
        return _appendable.toString();
    }
