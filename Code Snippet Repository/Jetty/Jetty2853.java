    @Override
    public int available()
    {
        int available = 0;
        boolean woken = false;
        synchronized (_inputQ)
        {
            if (_content == null)
                _content = _inputQ.poll();
            if (_content == null)
            {
                try
                {
                    produceContent();
                }
                catch (IOException e)
                {
                    woken = failed(e);
                }
                if (_content == null)
                    _content = _inputQ.poll();
            }

            if (_content != null)
                available = _content.remaining();
        }

        if (woken)
            wake();
        return available;
    }
