    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        baseRequest.setHandled(true);

        if (request.getContentType() != null)
            response.setContentType(request.getContentType());
        if (request.getParameter("charset") != null)
            response.setCharacterEncoding(request.getParameter("charset"));
        else if (request.getCharacterEncoding() != null)
            response.setCharacterEncoding(request.getCharacterEncoding());

        PrintWriter writer = response.getWriter();
        BufferedReader reader = request.getReader();
        int count = 0;
        String line;

        while ((line = reader.readLine()) != null)
        {
            writer.print(line);
            writer.print("\n");
            count += line.length();
        }

        // just to be difficult
        reader.close();
        writer.close();

        if (reader.read() >= 0)
            throw new IllegalStateException("Not closed");
    }
