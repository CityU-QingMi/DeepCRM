    public void run() 
    {  
        try 
        {
            while (true) 
            {
                checkSystemInput();
                getSomeSleep();
            }
        } 
        catch (IOException e) 
        {
            mojo.getLog().warn(e);
        }
    }
