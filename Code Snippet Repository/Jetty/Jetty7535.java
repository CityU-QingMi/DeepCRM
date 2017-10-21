    public void pause (int scavenge)
    {
        //Wait a little longer for the scavenging to happen with the JDBCSession handling.
        //The scavenging happens at about +10% longer than the scavenge interval, so that
        //not all nodes sync up and start trying to scavenge for the same sessions at the
        //same time.
        //So, we wait 3 times the scavenging interval.
        try
        {
            Thread.sleep(scavenge * 3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
