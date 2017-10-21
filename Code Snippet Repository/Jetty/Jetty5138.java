    public void setDebugEnabled(boolean enabled)
    {
        if (enabled)
        {
            configuredLevel = _logger.getLevel();
            _logger.setLevel(Level.FINE);
        }
        else
        {
            _logger.setLevel(configuredLevel);
        }
    }
