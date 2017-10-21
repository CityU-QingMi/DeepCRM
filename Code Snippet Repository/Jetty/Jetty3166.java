    @Override
    public boolean delete(String id) throws Exception
    {   
        File file = null;
        if (_storeDir != null)
        {
            file = getFile(_storeDir, id);
            if (file != null && file.exists() && file.getParentFile().equals(_storeDir))
            {
                return file.delete();
            }
        }
         
        return false;
    }
