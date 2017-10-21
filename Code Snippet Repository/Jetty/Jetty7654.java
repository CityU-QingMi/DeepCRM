    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException 
    {
        AsyncContext asyncContext = req.startAsync();
        MyAsyncListener listener = asyncContext.createListener(MyAsyncListener.class);

        PrintWriter writer = resp.getWriter();
        writer.println( "<html>");
        writer.println("<HEAD><link rel=\"stylesheet\" type=\"text/css\"  href=\"../stylesheet.css\"/></HEAD>");
        writer.println( "<body>");
        writer.println("<h1>AsyncListener</h2>");
        writer.println("<pre>");
        writer.println("<h2>@PostConstruct Callback</h2>");
        writer.println("<pre>");
        writer.println("@PostConstruct");
        writer.println("private void postConstruct ()");
        writer.println("{}"); 
        writer.println("</pre>");
        writer.println("<br/><b>Result: "+(listener.isPostConstructCalled()?"<span class=\"pass\">PASS</span>":"<span class=\"fail\">FAIL</span>")+"</b>");
        
        writer.println("<h2>@Resource Injection for env-entry </h2>");
        writer.println("<pre>");
        writer.println("@Resource(mappedName=\"maxAmount\")");
        writer.println("private Double maxAmount;");
        writer.println("</pre>");
        writer.println("<br/><b>Result: "+(listener.isResourceInjected()?" <span class=\"pass\">PASS</span>":" <span class=\"FAIL\">FAIL</span>")+"</b>");    
        
        writer.println( "</body>");
        writer.println( "</html>");
        writer.flush();
        writer.close();
        
        asyncContext.complete();
    }
