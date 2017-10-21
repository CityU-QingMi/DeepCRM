    public OptionalSessionCallableMethod(Class<?> pojo, Method method)
    {
        super(pojo,method);

        boolean foundConnection = false;
        boolean foundStreaming = false;

        if (paramTypes != null)
        {
            for (Class<?> paramType : paramTypes)
            {
                if (Session.class.isAssignableFrom(paramType))
                {
                    foundConnection = true;
                }
                if (Reader.class.isAssignableFrom(paramType) || InputStream.class.isAssignableFrom(paramType))
                {
                    foundStreaming = true;
                }
            }
        }

        this.wantsSession = foundConnection;
        this.streaming = foundStreaming;
    }
