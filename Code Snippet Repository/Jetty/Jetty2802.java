    public HomeBaseWarning()
    {
        boolean showWarn = false;
        
        String home = System.getProperty("jetty.home");
        String base = System.getProperty("jetty.base");

        if (StringUtil.isBlank(base))
        {
            // no base defined? then we are likely running
            // via direct command line.
            return;
        }

        Path homePath = new File(home).toPath();
        Path basePath = new File(base).toPath();

        try
        {
            showWarn = Files.isSameFile(homePath,basePath);
        }
        catch (IOException e)
        {
            LOG.ignore(e);
            // Can't definitively determine this state
            return;
        }

        if (showWarn)
        {
            StringBuilder warn = new StringBuilder();
            warn.append("This instance of Jetty is not running from a separate {jetty.base} directory");
            warn.append(", this is not recommended.  See documentation at http://www.eclipse.org/jetty/documentation/current/startup.html");
            LOG.warn("{}",warn.toString());
        }
    }
