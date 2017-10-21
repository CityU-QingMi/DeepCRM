    public void doStop()
        throws Exception
    {
        Object old_run_as = null;
        if (_servlet!=null)
        {
            try
            {
                if (_identityService!=null)
                    old_run_as=_identityService.setRunAs(_identityService.getSystemUserIdentity(),_runAsToken);

                destroyInstance(_servlet);
            }
            catch (Exception e)
            {
                LOG.warn(e);
            }
            finally
            {
                if (_identityService!=null)
                    _identityService.unsetRunAs(old_run_as);
            }
        }

        if (!_extInstance)
            _servlet=null;

        _config=null;
        _initialized = false;
    }
