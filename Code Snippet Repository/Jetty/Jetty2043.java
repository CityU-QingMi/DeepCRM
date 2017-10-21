    @Override
    public final void run()
    {
        final long timestamp = System.currentTimeMillis();
        final EventTrigger trigger = _action.getTrigger();

        _callback.execute(new Runnable() {
            public void run()
            {
                try
                {
                    if(trigger.match(timestamp))
                        _action.doExecute(timestamp);
                }
                catch (Exception ex)
                {
                    LOG.debug(ex);
                }
            }
        });
    }
