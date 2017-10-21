    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {   
        String mailTo = request.getParameter("mailto");
        String mailFrom = request.getParameter("mailfrom");
        
        if (mailTo != null)
            mailTo = mailTo.trim();
        
        if (mailFrom != null)
            mailFrom = mailFrom.trim();
        
        try
        {
            response.setContentType("text/html");
            ServletOutputStream out = response.getOutputStream();
            out.println("<html>");
            out.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet.css\"/></head>");
            out.println("<h1>Jetty JNDI Tests</h1>");
            out.println("<body>");
            
            out.println("<h2>Injection and JNDI Lookup Results</h2>");
            out.println("<p>"+resourceNameMappingInjectionResult+"</p>");
            out.println("<p>"+envEntryOverrideResult+"</p>");
            out.println("<p>"+postConstructResult+"</p>");
            out.println("<p>"+preDestroyResult+"</p>");
            out.println("<p>"+envEntryGlobalScopeResult+"</p>");
            out.println("<p>"+envEntryWebAppScopeResult+"</p>");
            out.println("<p>"+userTransactionResult+"</p>");
            out.println("<p>"+mailSessionResult+"</p>");
        

            out.println("</body>");            
            out.println("</html>");
            out.flush();
        }
        catch (Exception e)
        {
            throw new ServletException(e);
        }
    }
