    public static void endStartLog()
    {
        if (stderr!=err && getInstance().debug)
        {
            err.println("StartLog ended");
            stderr.println("StartLog ended");
        }
        setStream(stderr);
        System.setErr(stderr);
        System.setOut(stdout);
    }
