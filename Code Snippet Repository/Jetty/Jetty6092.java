    @Override
    public boolean process(Param param, JsrCallable callable) throws InvalidSignatureException
    {
        if (super.process(param,callable))
        {
            // Found common roles
            return true;
        }

        if (param.type.isAssignableFrom(Throwable.class))
        {
            param.bind(Role.ERROR_CAUSE);
            return true;
        }
        return false;
    }
