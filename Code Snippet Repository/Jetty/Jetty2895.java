    @Override
    protected void doStop() throws Exception
    {
        synchronized (this)
        {
            super.doStop();
            try
            {
                if (_writer != null)
                    _writer.flush();
            }
            catch (IOException e)
            {
                LOG.ignore(e);
            }
            if (_out != null && _closeOut)
                try
                {
                    _out.close();
                }
                catch (IOException e)
                {
                    LOG.ignore(e);
                }

            _out = null;
            _fileOut = null;
            _closeOut = false;
            _writer = null;
        }
    }
