    public LoggerLog(Object logger)
    {
        try
        {
            _logger = logger;
            Class<?> lc = logger.getClass();
            _debugMT = lc.getMethod("debug", new Class[]{String.class, Throwable.class});
            _debugMAA = lc.getMethod("debug", new Class[]{String.class, Object[].class});
            _infoMT = lc.getMethod("info", new Class[]{String.class, Throwable.class});
            _infoMAA = lc.getMethod("info", new Class[]{String.class, Object[].class});
            _warnMT = lc.getMethod("warn", new Class[]{String.class, Throwable.class});
            _warnMAA = lc.getMethod("warn", new Class[]{String.class, Object[].class});
            Method _isDebugEnabled = lc.getMethod("isDebugEnabled");
            _setDebugEnabledE = lc.getMethod("setDebugEnabled", new Class[]{Boolean.TYPE});
            _getLoggerN = lc.getMethod("getLogger", new Class[]{String.class});
            _getName = lc.getMethod("getName");

            _debug = (Boolean)_isDebugEnabled.invoke(_logger);
        }
        catch(Exception x)
        {
            throw new IllegalStateException(x);
        }
    }
