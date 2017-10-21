    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws ServletException, IOException
    {
        handleForm(request,response);

        response.setContentType("text/html");


        PrintWriter out = response.getWriter();
        out.println("<h1>Cookie Dump Servlet:</h1>");

        Cookie[] cookies = request.getCookies();

        for (int i=0;cookies!=null && i<cookies.length;i++)
        {
            out.println("<b>"+deScript(cookies[i].getName())+"</b>="+deScript(cookies[i].getValue())+"<br/>");
        }

        out.println("<form action=\""+response.encodeURL(getURI(request))+"\" method=\"post\">");

        out.println("<b>Name:</b><input type=\"text\" name=\"Name\" value=\"name\"/><br/>");
        out.println("<b>Value:</b><input type=\"text\" name=\"Value\" value=\"value\"/><br/>");
        out.println("<b>Max-Age:</b><input type=\"text\" name=\"Age\" value=\"60\"/><br/>");
        out.println("<input type=\"submit\" name=\"Action\" value=\"Set\"/>");

    }
