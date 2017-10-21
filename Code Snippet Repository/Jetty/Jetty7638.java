    private void runClassloaderChecks(ServletOutputStream out) throws Exception
    {
        out.println("    <h1>Checking Classloader Setup</h1>");
        out.println("      <p>");

        System.getProperty("user.dir");
        try
        {
            out.println("check ability to create classloader<br/>");
            URL url = new URL("http://not.going.to.work");
            new URLClassLoader(new URL[] { url });
            out.println("status: <b>SUCCESS - unexpected</b><br/>");
        }
        catch (SecurityException e)
        {
            out.println("status: <b>FAILURE - expected</b><br/>");
        }

        out.println("      </p><br/><br/>");
    }
