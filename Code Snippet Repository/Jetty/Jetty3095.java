    public Resource getStylesheet()
    {
        if (_stylesheet != null)
        {
            return _stylesheet;
        }
        else
        {
            if (_defaultStylesheet == null)
            {
                _defaultStylesheet = Resource.newResource(this.getClass().getResource("/jetty-dir.css"));
            }
            return _defaultStylesheet;
        }
    }
