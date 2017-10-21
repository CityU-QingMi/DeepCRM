    private void detectJspContainer ()
    {
        if (_jspContainer == null)
        {
            try
            {
                //check for apache
                Loader.loadClass(APACHE_SENTINEL_CLASS);
                if (LOG.isDebugEnabled())LOG.debug("Apache jasper detected");
                _jspContainer = JspContainer.APACHE;
            }
            catch (ClassNotFoundException x)
            {
                if (LOG.isDebugEnabled())LOG.debug("Other jasper detected");
                _jspContainer = JspContainer.OTHER;
            }
        }
    }
