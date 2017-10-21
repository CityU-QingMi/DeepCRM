    @Override
    public synchronized int read() throws IOException
    {
        int b = super.read();
        if (_skipLF)
        {
            _skipLF=false;
            if (_seenCRLF && b=='\n')
                b=super.read();
        }
        return b;
    }
