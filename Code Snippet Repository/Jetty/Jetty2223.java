    protected static boolean diagnoseNonActiveOrNonResolvedBundle(Bundle b)
    {
        if (b.getState() != Bundle.ACTIVE && b.getHeaders().get("Fragment-Host") == null)
        {
            try
            {
                System.err.println("Trying to start the bundle " + b.getSymbolicName() + " that was supposed to be active or resolved.");
                b.start();
                System.err.println(b.getSymbolicName() + " did start");
                return true;
            }
            catch (Throwable t)
            {
                System.err.println(b.getSymbolicName() + " failed to start");
                t.printStackTrace(System.err);
                return false;
            }
        }
        System.err.println(b.getSymbolicName() + " was already started");
        return false;
    }
