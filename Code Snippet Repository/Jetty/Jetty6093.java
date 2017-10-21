    protected void assertPartialMessageSupportDisabled(Param param, JsrCallable callable)
    {
        if (callable instanceof OnMessageCallable)
        {
            OnMessageCallable onmessage = (OnMessageCallable)callable;
            if (onmessage.isPartialMessageSupported())
            {
                StringBuilder err = new StringBuilder();
                err.append("Unable to support parameter type <");
                err.append(param.type.getName()).append("> in conjunction with the partial message indicator boolean.");
                err.append(" Only type <String> is supported with partial message boolean indicator.");
                throw new InvalidSignatureException(err.toString());
            }
        }
    }
