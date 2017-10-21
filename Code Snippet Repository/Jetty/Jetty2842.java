    private int fillRequestBuffer()
    {
        if (_contentBufferReferences.get()>0)
        {
            LOG.warn("{} fill with unconsumed content!",this);
            return 0;
        }

        if (BufferUtil.isEmpty(_requestBuffer))
        {
            // Can we fill?
            if(getEndPoint().isInputShutdown())
            {
                // No pretend we read -1
                _parser.atEOF();
                if (LOG.isDebugEnabled())
                    LOG.debug("{} filled -1 {}",this,BufferUtil.toDetailString(_requestBuffer));
                return -1;
            }

            // Get a buffer
            // We are not in a race here for the request buffer as we have not yet received a request,
            // so there are not an possible legal threads calling #parseContent or #completed.
            _requestBuffer = getRequestBuffer();

            // fill
            try
            {
                int filled = getEndPoint().fill(_requestBuffer);
                if (filled==0) // Do a retry on fill 0 (optimization for SSL connections)
                    filled = getEndPoint().fill(_requestBuffer);

                // tell parser
                if (filled < 0)
                    _parser.atEOF();

                if (LOG.isDebugEnabled())
                    LOG.debug("{} filled {} {}",this,filled,BufferUtil.toDetailString(_requestBuffer));

                return filled;
            }
            catch (IOException e)
            {
                LOG.debug(e);
                _parser.atEOF();
                return -1;
            }
        }
        return 0;
    }
