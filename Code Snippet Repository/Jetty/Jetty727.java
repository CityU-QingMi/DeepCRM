    private void fauxSuspend()
    {
        long expire_at = System.currentTimeMillis()+_timeoutMs;
        long wait=_timeoutMs;
        while (_timeoutMs>0 && wait>0)
        {
            try
            {
                this.wait(wait);
            }
            catch (InterruptedException e)
            {
                break;
            }
            wait=expire_at-System.currentTimeMillis();
        }

        if (_timeoutMs>0 && wait<=0)
            expire();
    }
