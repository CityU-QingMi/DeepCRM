    @Override
    public SessionData load(String id) throws Exception
    {
        SessionData sd = _map.get(id);
        if (sd == null)
            return null;
        SessionData nsd = new SessionData(id,"","",System.currentTimeMillis(),System.currentTimeMillis(), System.currentTimeMillis(),0 );
        nsd.copy(sd);
        return nsd;
    }
