    @Override
    protected void doStop() throws Exception
    {
        if (lease!=null)
            lease.close();

        // Tell the acceptors we are stopping
        interruptAcceptors();

        // If we have a stop timeout
        long stopTimeout = getStopTimeout();
        CountDownLatch stopping=_stopping;
        if (stopTimeout > 0 && stopping!=null && getAcceptors()>0)
            stopping.await(stopTimeout,TimeUnit.MILLISECONDS);
        _stopping=null;

        super.doStop();

        for (Acceptor a : getBeans(Acceptor.class))
            removeBean(a);

        LOG.info("Stopped {}", this);
    }
