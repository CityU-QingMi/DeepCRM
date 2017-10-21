    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        response.setContentType("text/html");
        ServletOutputStream out = response.getOutputStream();
        out.println("<html>");
        out.println("  <title>Secure Jetty Test Webapp</title>");

        try
        {
            runPropertyChecks(out);

            runFileSystemChecks(out);

            runLoggingChecks(out);

            runClassloaderChecks(out);
        }
        catch (Exception e)
        {
            e.printStackTrace(new PrintStream(out));
        }
        out.println("</html>");
        out.flush();

        try
        {
            Thread.sleep(200);
        }
        catch (InterruptedException e)
        {
            getServletContext().log("exception",e);
        }
    }
