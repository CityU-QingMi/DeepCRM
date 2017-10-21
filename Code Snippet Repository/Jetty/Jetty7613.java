    public static void pause (int sec)
    {
        try
        {
            Thread.currentThread().sleep(sec*1000L);
        }
        catch (InterruptedException e)
        {
            //just return;
        }
    }
