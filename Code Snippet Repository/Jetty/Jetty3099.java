    public void setStylesheet(String stylesheet)
    {
        try
        {
            _stylesheet = Resource.newResource(stylesheet);
            if (!_stylesheet.exists())
            {
                LOG.warn("unable to find custom stylesheet: " + stylesheet);
                _stylesheet = null;
            }
        }
        catch (Exception e)
        {
            LOG.warn(e.toString());
            LOG.debug(e);
            throw new IllegalArgumentException(stylesheet);
        }
    }
