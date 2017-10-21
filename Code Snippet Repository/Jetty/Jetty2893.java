    @Override
    public void write(String requestEntry) throws IOException
    {
        synchronized(this)
        {
            if (_writer==null)
                return;
            _writer.write(requestEntry);
            _writer.write(StringUtil.__LINE_SEPARATOR);
            _writer.flush();
        }
    }
