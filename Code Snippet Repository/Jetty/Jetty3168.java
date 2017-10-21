    @Override
    public SessionData load(String id) throws Exception
    {  
        final AtomicReference<SessionData> reference = new AtomicReference<SessionData>();
        final AtomicReference<Exception> exception = new AtomicReference<Exception>();
        Runnable r = new Runnable()
        {
            public void run ()
            {
                //get rid of all but the newest file for a session
                File file = deleteOldFiles(_storeDir, getIdWithContext(id));
   
                if (file == null || !file.exists())
                {
                    if (LOG.isDebugEnabled())
                        LOG.debug("No file: {}",file);
                    return;
                }

                try (FileInputStream in = new FileInputStream(file))
                {
                    SessionData data = load(in, id);
                    data.setLastSaved(file.lastModified());
                    reference.set(data);
                }
                catch (UnreadableSessionDataException e)
                {
                    if (isDeleteUnrestorableFiles() && file.exists() && file.getParentFile().equals(_storeDir))
                    {
                        file.delete();
                        LOG.warn("Deleted unrestorable file for session {}", id);
                    }

                    exception.set(e);
                }
                catch (Exception e)
                {
                    exception.set(e);
                }
            }
        };
        //ensure this runs with the context classloader set
        _context.run(r);
        
        if (exception.get() != null)
            throw exception.get();
        
        return reference.get();
    }
