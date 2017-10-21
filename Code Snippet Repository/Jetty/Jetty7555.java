    @Override
    public void assertSession(String id, boolean exists)
    {
       assertNotNull(_dataStore);
       try
       {
           boolean inmap = _dataStore.exists(id);
           if (exists)
               assertTrue(inmap);
           else
               assertFalse(inmap);
       }
       catch (Exception e)
       {
           fail(e.getMessage());
       }
    }
