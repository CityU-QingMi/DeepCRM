    public void init(JsrSession session)
    {
        session.setPathParameters(pathParameters);

        if (onOpen != null)
        {
            onOpen.init(session);
        }
        if (onClose != null)
        {
            onClose.init(session);
        }
        if (onError != null)
        {
            onError.init(session);
        }
        if (onText != null)
        {
            onText.init(session);
        }
        if (onTextStream != null)
        {
            onTextStream.init(session);
        }
        if (onBinary != null)
        {
            onBinary.init(session);
        }
        if (onBinaryStream != null)
        {
            onBinaryStream.init(session);
        }
        if (onPong != null)
        {
            onPong.init(session);
        }
    }
