    protected void log(String format, Object... arg)
    {
        if (!isRunning())
            return;
        
        String s=String.format(format,arg);
        
        long now = System.currentTimeMillis();
        long ms = now%1000;
        if (_out!=null)
            _out.printf("%s.%03d:%s%n",__date.formatNow(now),ms,s);
        if (LOG.isDebugEnabled())
            LOG.info(s);
    }
