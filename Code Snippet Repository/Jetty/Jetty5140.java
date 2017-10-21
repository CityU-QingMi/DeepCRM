    private void log(Marker marker, int level, String msg, Object[] argArray, Throwable t)
    {
        if (argArray == null)
        {
            // Simple SLF4J Message (no args)
            _logger.log(marker, FQCN, level, msg, null, t);
        }
        else
        {
            int loggerLevel = _logger.isTraceEnabled() ? TRACE :
                    _logger.isDebugEnabled() ? DEBUG :
                            _logger.isInfoEnabled() ? INFO :
                                    _logger.isWarnEnabled() ? WARN : ERROR;
            if (loggerLevel <= level)
            {
                // Don't assume downstream handles argArray properly.
                // Do it the SLF4J way here to eliminate that as a bug.
                FormattingTuple ft = MessageFormatter.arrayFormat(msg, argArray);
                _logger.log(marker, FQCN, level, ft.getMessage(), null, t);
            }
        }
    }
