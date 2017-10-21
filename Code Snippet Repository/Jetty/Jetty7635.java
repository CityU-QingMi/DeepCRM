    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        ServletOutputStream out = response.getOutputStream();
        out.println("<html>");
        out.println("<br/>Before getUserPrincipal="+request.getUserPrincipal());
        out.println("<br/>Before getRemoteUser="+request.getRemoteUser());
        String param = request.getParameter("action");

        if ("login".equals(param))
        {
              request.login("jetty", "jetty");
        }
        else if ("logout".equals(param))
        {
             request.logout();
        }
        else if ("wrong".equals(param))
        {
             request.login("jetty", "123");
        }  

        out.println("<br/>After getUserPrincipal="+request.getUserPrincipal());
        out.println("<br/>After getRemoteUser="+request.getRemoteUser());
        out.println("</html>");
        out.flush();
    }
