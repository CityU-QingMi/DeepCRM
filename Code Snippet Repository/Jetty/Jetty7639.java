    private void runLoggingChecks(ServletOutputStream out) throws Exception
    {
        out.println("    <h1>Checking File System</h1>");
        out.println("      <p>");

        String userDir = System.getProperty("user.dir");
        try
        {
            out.println("check ability to log<br/>");
            LOG.info("testing logging");
            out.println("status: <b>SUCCESS - expected</b><br/>");
        }
        catch (SecurityException e)
        {
            out.println("status: <b>FAILURE - unexpected</b><br/>");
            out.println("<table><tr><td>");
            e.printStackTrace(new PrintStream(out));
            out.println("</td></tr></table>");
        }

        try
        {
            Calendar c = new GregorianCalendar();

            String logFile = c.get(Calendar.YEAR) + "_" + c.get(Calendar.MONTH) + "_" + c.get(Calendar.DAY_OF_MONTH) + ".request.log";

            out.println("check ability to access log file directly<br/>");
            File jettyHomeFile = new File(userDir + File.separator + "logs" + File.separator + logFile);
            jettyHomeFile.canRead();
            out.println("status: <b>SUCCESS - unexpected</b><br/>");
        }
        catch (SecurityException e)
        {
            out.println("status: <b>FAILURE - expected</b><br/>");
        }

        out.println("      </p><br/><br/>");
    }
