    public EventMethod(Class<?> pojo, String methodName, Class<?>... paramTypes)
    {
        try
        {
            this.pojo = pojo;
            this.paramTypes = paramTypes;
            this.method = pojo.getMethod(methodName,paramTypes);
            identifyPresentParamTypes();
        }
        catch (NoSuchMethodException | SecurityException e)
        {
            LOG.warn("Cannot use method {}({}): {}",methodName,paramTypes,e.getMessage());
            this.method = null;
        }
    }
