    public static final void main(String[] args)
    {
        if (args == null)
           System.exit(1);
       
       Starter starter = null;
       try
       {
           starter = new Starter();
           starter.getConfiguration(args);
           starter.configureJetty();
           starter.run();
           starter.communicateStartupResult(null);
           starter.join();
       }
       catch (Exception e)
       {
           starter.communicateStartupResult(e);
           e.printStackTrace();
           System.exit(1);
       }

    }
