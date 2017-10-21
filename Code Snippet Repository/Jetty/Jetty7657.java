    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {      
        try
        {
            response.setContentType("text/html");
            ServletOutputStream out = response.getOutputStream();
            out.println("<html>");
            out.println("<HEAD><link rel=\"stylesheet\" type=\"text/css\"  href=\"stylesheet.css\"/></HEAD>");
            out.println("<h1>Jetty DeclareRoles Annotation Results</h1>");
            out.println("<body>");
            
            out.println("<h2>Roles</h2>");
            boolean result = request.isUserInRole("other");
            out.println("<br/><b>Result: isUserInRole(\"other\")="+result+":"+ (result==false?" <span class=\"pass\">PASS":" <span class=\"fail\">FAIL")+"</span></b>");

            result = request.isUserInRole("manager");
            out.println("<br/><b>Result: isUserInRole(\"manager\")="+result+":"+ (result?" <span class=\"pass\">PASS":" <span class=\"fail\">FAIL")+"</span></b>");
            result = request.isUserInRole("user");
            out.println("<br/><b>Result: isUserInRole(\"user\")="+result+":"+ (result?" <span class=\"pass\">PASS":" <span class=\"fail\">FAIL")+"</span></b>");
            String context = _config.getServletContext().getContextPath();
            if (!context.endsWith("/"))
                context += "/";
            
            out.println("<p><A HREF=\""+context+"logout.jsp\">Logout</A></p>");
            
            out.println("</body>");            
            out.println("</html>");
            out.flush();
        }
        catch (Exception e)
        {
            throw new ServletException(e);
        }
    }
