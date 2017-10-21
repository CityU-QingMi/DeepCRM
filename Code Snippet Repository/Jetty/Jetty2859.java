    public boolean addContent(Content content)
    {
        synchronized (_inputQ)
        {
            _waitingForContent = false;
            if (_firstByteTimeStamp == -1)
                _firstByteTimeStamp = System.nanoTime();

            if (isFinished())
            {
                Throwable failure = isError() ? _state.getError() : new EOFException("Content after EOF");
                content.failed(failure);
                return false;
            }
            else
            {
                _contentArrived += content.remaining();
            
                if (_content==null && _inputQ.isEmpty())
                    _content=content;
                else
                    _inputQ.offer(content);
            
                if (LOG.isDebugEnabled())
                    LOG.debug("{} addContent {}",this,content);

                if (nextInterceptedContent()!=null)
                    return wakeup();
                else
                    return false;
            }
        }
    }
