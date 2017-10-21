    @Override
    public void setDebugEnabled(boolean enabled)
    {
        if (enabled)
        {
            this._level = LEVEL_DEBUG;

            for (Logger log : Log.getLoggers().values())
            {                
                if (log.getName().startsWith(getName()) && log instanceof StdErrLog)
                    ((StdErrLog)log).setLevel(LEVEL_DEBUG);
            }
        }
        else
        {
            this._level = this._configuredLevel;

            for (Logger log : Log.getLoggers().values())
            {
                if (log.getName().startsWith(getName()) && log instanceof StdErrLog)
                    ((StdErrLog)log).setLevel(((StdErrLog)log)._configuredLevel);
            }
        }
    }
