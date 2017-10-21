    @Override
    public boolean exists(String id) throws Exception
    {
       File sessionFile = deleteOldFiles(_storeDir, getIdWithContext(id));
       if (sessionFile == null || !sessionFile.exists())
           return false;
       
       //check the expiry
       long expiry = getExpiryFromFile(sessionFile);
       if (expiry <= 0)
           return true; //never expires
       else
           return (expiry > System.currentTimeMillis()); //hasn't yet expired
    }
