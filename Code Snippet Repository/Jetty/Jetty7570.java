    public void pause(int scavenge)
    {
        try
        {
            Thread.sleep(scavenge * 1000L);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
