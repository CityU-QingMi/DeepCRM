    @Override
    public void setContentLength(int len)
    {
        // Protect from setting after committed as default handling
        // of a servlet HEAD request ALWAYS sets _content length, even
        // if the getHandling committed the response!
        if (isCommitted() || isIncluding())
            return;

        if (len>0)
        {
            long written = _out.getWritten();
            if (written > len)
                throw new IllegalArgumentException("setContentLength(" + len + ") when already written " + written);

            _contentLength = len;
            _fields.putLongField(HttpHeader.CONTENT_LENGTH, len);
            if (isAllContentWritten(written))
            {
                try
                {
                    closeOutput();
                }
                catch(IOException e)
                {
                    throw new RuntimeIOException(e);
                }
            }
        }
        else if (len==0)
        {
            long written = _out.getWritten();
            if (written > 0)
                throw new IllegalArgumentException("setContentLength(0) when already written " + written);
            _contentLength = len;
            _fields.put(HttpHeader.CONTENT_LENGTH, "0");
        }
        else
        {
            _contentLength = len;
            _fields.remove(HttpHeader.CONTENT_LENGTH);
        }
    }
