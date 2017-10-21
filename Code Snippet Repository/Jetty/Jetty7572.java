    public void pause(int msec)
    {
        try
        {
            Thread.sleep(msec);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
