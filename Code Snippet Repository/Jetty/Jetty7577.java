    public void pause(int scavengePeriod)
    {
        try
        {
            Thread.sleep(scavengePeriod * 1000L);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
