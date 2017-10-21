    @Override
    protected synchronized void doStart() throws Exception
    {
        if (_filename != null)
        {
            _fileOut = new RolloverFileOutputStream(_filename,_append,_retainDays,TimeZone.getTimeZone(getLogTimeZone()),_filenameDateFormat,null);
            _closeOut = true;
            LOG.info("Opened " + getDatedFilename());
        }
        else
            _fileOut = System.err;

        _out = _fileOut;

        synchronized(this)
        {
            _writer = new OutputStreamWriter(_out);
        }
        super.doStart();
    }
