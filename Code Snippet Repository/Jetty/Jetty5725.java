    private void setLoggerOptions(Class<?> clazz, StdErrCapture output)
    {
        Logger logger = Log.getLogger(clazz);
        logger.setDebugEnabled(true);

        if (logger instanceof StdErrLog)
        {
            StdErrLog sel = (StdErrLog)logger;
            sel.setPrintLongNames(true);
            output.capture(sel);
        }
    }
