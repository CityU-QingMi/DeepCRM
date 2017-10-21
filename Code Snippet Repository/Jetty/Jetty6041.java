    @Override
    public void onSessionClosed(WebSocketSession session)
    {
        if (session instanceof Session)
        {
            removeBean(session);
        }
        else
        {
            LOG.warn("JSR356 Implementation should not be mixed with native implementation: Expected {} to implement {}",session.getClass().getName(),
                    Session.class.getName());
        }
    }
