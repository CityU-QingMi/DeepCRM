    public QuickStartDescriptorProcessor()
    {
        try
        {
            registerVisitor("context-param", this.getClass().getMethod("visitContextParam", __signature));
            registerVisitor("servlet-mapping", this.getClass().getMethod("visitServletMapping", __signature));
        }    
        catch (Exception e)
        {
            throw new IllegalStateException(e);
        }
    }
