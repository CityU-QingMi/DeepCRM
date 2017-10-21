    public void setRequestLog(String className)
    {
        try
        {
            this.requestLog = (RequestLog) Class.forName(className).newInstance();
        }
        catch (InstantiationException e)
        {
            throw new BuildException("Request logger instantiation exception: " + e);
        }
        catch (IllegalAccessException e)
        {
            throw new BuildException("Request logger instantiation exception: " + e);
        }
        catch (ClassNotFoundException e)
        {
            throw new BuildException("Unknown request logger class: " + className);
        }
    }
