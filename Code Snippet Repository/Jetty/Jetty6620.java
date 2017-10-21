    @Override
    public void onUpgradeTo(ByteBuffer prefilled)
    {
        if(LOG.isDebugEnabled())
        {
            LOG.debug("onUpgradeTo({})", BufferUtil.toDetailString(prefilled));
        }
    
        setInitialBuffer(prefilled);
    }
