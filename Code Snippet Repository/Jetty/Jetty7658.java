    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException 
    {
        PrintWriter writer = resp.getWriter();
        writer.println( "<html>");
        writer.println("<HEAD><link rel=\"stylesheet\" type=\"text/css\"  href=\"../stylesheet.css\"/></HEAD>");
        writer.println("<h1>@ServletSecurity</h1>");
        writer.println("<body>");
        writer.println("<pre>");
        writer.println("@ServletSecurity");
        writer.println("public class SecuredServlet");
        writer.println("</pre>");
        writer.println("<p><b>Result: <span class=\"pass\">PASS</span></b></p>");
        String context = getServletConfig().getServletContext().getContextPath();
        if (!context.endsWith("/"))
            context += "/";
        writer.println("<p><A HREF=\""+context+"logout.jsp\">Logout</A></p>");
        writer.println( "</body>");
        writer.println( "</html>");
        writer.flush();
        writer.close();
    }
