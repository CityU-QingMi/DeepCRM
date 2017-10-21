    private void identifyPresentParamTypes()
    {
        this.hasSession = false;
        this.isStreaming = false;

        if (paramTypes == null)
        {
            return;
        }

        for (Class<?> paramType : paramTypes)
        {
            if (Session.class.isAssignableFrom(paramType))
            {
                this.hasSession = true;
            }
            if (Reader.class.isAssignableFrom(paramType) || InputStream.class.isAssignableFrom(paramType))
            {
                this.isStreaming = true;
            }
        }
    }
