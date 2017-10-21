    public PlusDescriptorProcessor ()
    {
        try
        {
            registerVisitor("env-entry", getClass().getMethod("visitEnvEntry", __signature));
            registerVisitor("resource-ref", getClass().getMethod("visitResourceRef", __signature));
            registerVisitor("resource-env-ref", getClass().getMethod("visitResourceEnvRef", __signature));
            registerVisitor("message-destination-ref", getClass().getMethod("visitMessageDestinationRef", __signature));
            registerVisitor("post-construct", getClass().getMethod("visitPostConstruct", __signature));
            registerVisitor("pre-destroy", getClass().getMethod("visitPreDestroy", __signature));
        }
        catch (Exception e)
        {
            throw new IllegalStateException(e);
        }
    }
