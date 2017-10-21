    protected AbstractJsrRemote(JsrSession session)
    {
        this.session = session;
        if (!(session.getRemote() instanceof WebSocketRemoteEndpoint))
        {
            StringBuilder err = new StringBuilder();
            err.append("Unexpected implementation [");
            err.append(session.getRemote().getClass().getName());
            err.append("].  Expected an instanceof [");
            err.append(WebSocketRemoteEndpoint.class.getName());
            err.append("]");
            throw new IllegalStateException(err.toString());
        }
        this.jettyRemote = (WebSocketRemoteEndpoint)session.getRemote();
        this.encoders = session.getEncoderFactory();
    }
