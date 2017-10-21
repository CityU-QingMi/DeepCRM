    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        ServletOutputStream out = res.getOutputStream();
        out.println("<html><body><table>");
        out.println("<tr><th>Original request URI: </th><td>" + req.getAttribute("requestedPath") + "</td></tr>");
        out.println("<tr><th>Rewritten request URI: </th><td>" + req.getRequestURI() + "</td></tr>");

        Cookie cookie = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null)
        {
            for(Cookie c: cookies)
            {
                if (c.getName().equals("visited"))
                {
                    cookie = c;
                    break;
                }
            }
        }
        if (cookie!=null)
            out.println("<tr><th>Previously visited: </th></td><td>" + cookie.getValue()+"</td></tr>");

        out.println("</table></body></html>");
    }
