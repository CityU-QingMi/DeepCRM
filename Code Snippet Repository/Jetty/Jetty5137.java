    protected void log(Level level,String msg,Throwable thrown)
    {
        LogRecord record = new LogRecord(level,msg);
        if (thrown!=null)
            record.setThrown(thrown);
        record.setLoggerName(_logger.getName());
        if (__source)
        {
            StackTraceElement[] stack = new Throwable().getStackTrace();
            for (int i=0;i<stack.length;i++)
            {
                StackTraceElement e=stack[i];
                if (!e.getClassName().equals(THIS_CLASS))
                {
                    record.setSourceClassName(e.getClassName());
                    record.setSourceMethodName(e.getMethodName());
                    break;
                }
            }
        }
        _logger.log(record);
    }
