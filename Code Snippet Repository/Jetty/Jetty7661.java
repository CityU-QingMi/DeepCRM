    protected void doTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.addHeader("Content-Type","message/http");
        StringBuffer msg = new StringBuffer();
        msg.append(request.getMethod()).append(' ');
        msg.append(request.getRequestURI()).append(' ');
        msg.append(request.getProtocol()).append("\n");

        // Now the headers
        Enumeration enNames = request.getHeaderNames();
        while (enNames.hasMoreElements())
        {
            String name = (String)enNames.nextElement();
            Enumeration enValues = request.getHeaders(name);
            while (enValues.hasMoreElements())
            {
                String value = (String)enValues.nextElement();
                msg.append(name).append(": ").append(value).append("\n");
            }
        }
        msg.append("\n");

        response.getWriter().print(msg.toString());
    }
