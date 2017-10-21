    @Override
    public void doStore(String id, SessionData data, long lastSaveTime) throws Exception
    {
        File file = null;
        if (_storeDir != null)
        {
            //remove any existing files for the session
            deleteAllFiles(_storeDir, getIdWithContext(id));
            
            //make a fresh file using the latest session expiry
            file = new File(_storeDir, getIdWithContextAndExpiry(data));

            try(FileOutputStream fos = new FileOutputStream(file,false))
            {
                save(fos, id, data);
            }
            catch (Exception e)
            { 
                e.printStackTrace();
                if (file != null) 
                    file.delete(); // No point keeping the file if we didn't save the whole session
                throw new UnwriteableSessionDataException(id, _context,e);             
            }
        }
    }
