    public void setWebappContext(WebAppContext webappContext)
    {
        try
        {
            if (_contextField == null)
            {
                _contextField = WebAppClassLoader.class.getDeclaredField("_context");
                _contextField.setAccessible(true);
            }
            _contextField.set(this, webappContext);
            if (webappContext.getExtraClasspath() != null)
            {
                addClassPath(webappContext.getExtraClasspath());
            }
        }
        catch (Throwable t)
        {
            // humf that will hurt if it does not work.
            __logger.warn("Unable to set webappcontext", t);
        }
    }
