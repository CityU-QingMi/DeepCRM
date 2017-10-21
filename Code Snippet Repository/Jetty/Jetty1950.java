    private void getSomeSleep() 
    {
        try 
        {
            Thread.sleep(500);
        } 
        catch (InterruptedException e) 
        {
            mojo.getLog().debug(e);
        }
    }
