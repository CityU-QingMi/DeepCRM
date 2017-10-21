    public void willPassivate()
    {
        HttpSessionEvent event = new HttpSessionEvent(this);
        for (Iterator<String> iter = _sessionData.getKeys().iterator(); iter.hasNext();)
        {
            Object value = _sessionData.getAttribute(iter.next());
            if (value instanceof HttpSessionActivationListener)
            {
                HttpSessionActivationListener listener = (HttpSessionActivationListener) value;
                listener.sessionWillPassivate(event);
            }
        }
    }
