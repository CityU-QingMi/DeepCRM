    public PreconfigureDescriptorProcessor ()
    {
        _showOrigin=LOG.isDebugEnabled();
        try
        {
            registerVisitor("env-entry", getClass().getMethod("saveSnippet", __signature));
            registerVisitor("resource-ref", getClass().getMethod("saveSnippet", __signature));
            registerVisitor("resource-env-ref", getClass().getMethod("saveSnippet", __signature));
            registerVisitor("message-destination-ref", getClass().getMethod("saveSnippet", __signature));
            registerVisitor("data-source", getClass().getMethod("saveSnippet", __signature));
        }
        catch (Exception e)
        {
            throw new IllegalStateException(e);
        }
    }
