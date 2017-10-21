    @Override
    public final void openSession(WebSocketSession session)
    {
        // Cast should be safe, as it was created by JsrSessionFactory
        this.jsrsession = (JsrSession)session;

        // Allow jsr session to init
        this.jsrsession.init(config);

        // Allow event driver to init itself
        init(jsrsession);

        // Allow end-user socket to adjust configuration
        super.openSession(session);
    }
