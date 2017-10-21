    @Override
    public void onSessionOpened(WebSocketSession session)
    {
        if (session instanceof Session)
        {
            addManaged(session);
        }
        else
        {
            LOG.warn("JSR356 Implementation should not be mixed with native implementation: Expected {} to implement {}",session.getClass().getName(),
                    Session.class.getName());
        }
    }
