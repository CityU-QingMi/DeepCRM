    public void pause(int period)
    {
        try
        {
            Thread.sleep(period * 1000L);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
