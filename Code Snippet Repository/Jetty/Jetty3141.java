    @Override
    public void store(String id, SessionData data) throws Exception
    {
        if (data == null)
            return;


        long lastSave = data.getLastSaved();
        long savePeriodMs = (_savePeriodSec <=0? 0: TimeUnit.SECONDS.toMillis(_savePeriodSec));
        
        if (LOG.isDebugEnabled())
            LOG.debug("Store: id={}, dirty={}, lsave={}, period={}, elapsed={}", id,data.isDirty(), data.getLastSaved(), savePeriodMs, (System.currentTimeMillis()-lastSave));

        //save session if attribute changed or never been saved or time between saves exceeds threshold
        if (data.isDirty() || (lastSave <= 0) || ((System.currentTimeMillis()-lastSave) > savePeriodMs))
        {
            //set the last saved time to now
            data.setLastSaved(System.currentTimeMillis());
            try
            {
                //call the specific store method, passing in previous save time
                doStore(id, data, lastSave);
                data.setDirty(false); //only undo the dirty setting if we saved it
            }
            catch (Exception e)
            {
                //reset last save time if save failed
                data.setLastSaved(lastSave);
                throw e;
            }
        }
    }
