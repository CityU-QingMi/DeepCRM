    @Override
    public void run()
    {
        Timer timer = _timer;
        if (timer != null)
        {
            timer.purge();
            schedule(this, 1, TimeUnit.SECONDS);
        }
    }
