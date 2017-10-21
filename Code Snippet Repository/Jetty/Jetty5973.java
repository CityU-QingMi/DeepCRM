    @Override
    protected void doStart() throws Exception
    {
        try
        {
            _metadata.setAllowDuplicateFragmentNames(isAllowDuplicateFragmentNames());
            Boolean validate = (Boolean)getAttribute(MetaData.VALIDATE_XML);
            _metadata.setValidateXml((validate!=null && validate.booleanValue()));
            preConfigure();
            super.doStart();
            postConfigure();

            if (isLogUrlOnStart())
                dumpUrl();
        }
        catch (Exception e)
        {
            //start up of the webapp context failed, make sure it is not started
            LOG.warn("Failed startup of context "+this, e);
            _unavailableException=e;
            setAvailable(false);
            if (isThrowUnavailableOnStartupException())
                throw e;
        }
    }
