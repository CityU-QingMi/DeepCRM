    @Override
    public void doStore(String id, SessionData data, long lastSaveTime) throws Exception
    {
        if (data==null || id==null)
            return;

        if (lastSaveTime <= 0)
        {     
            doInsert(id, data);
        }
        else
        {
            doUpdate(id, data);            
        }
    }
