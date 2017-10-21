    @Override
    public Session doGet(String id)
    {
        if (id == null)
            return null;
        
        Session session = _sessions.get(id);
       
        return session;
    }
