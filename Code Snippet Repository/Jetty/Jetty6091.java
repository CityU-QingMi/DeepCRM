    @Override
    public boolean process(Param param, JsrCallable callable) throws InvalidSignatureException
    {
        if (super.process(param,callable))
        {
            // Found common roles
            return true;
        }

        if (param.type.isAssignableFrom(CloseReason.class))
        {
            param.bind(Role.CLOSE_REASON);
            return true;
        }
        return false;
    }
