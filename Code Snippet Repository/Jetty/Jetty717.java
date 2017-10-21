    private void debug(String string, Throwable th)
    {
        if (_debug)
        {
            if (th instanceof ContinuationThrowable)
                _context.log(string+":"+th);
            else
                _context.log(string,th);
        }
    }
