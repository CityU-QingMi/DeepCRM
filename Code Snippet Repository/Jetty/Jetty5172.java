    @Override
    protected Logger newLogger(String fullname)
    {
        StdErrLog logger = new StdErrLog(fullname);
        // Preserve configuration for new loggers configuration
        logger.setPrintLongNames(_printLongNames);
        logger._stderr = this._stderr;

        // Force the child to have any programmatic configuration
        if (_level!=_configuredLevel)
            logger._level=_level;

        return logger;
    }
