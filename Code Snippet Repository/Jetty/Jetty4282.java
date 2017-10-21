    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Boolean suspended = (Boolean)request.getAttribute("SUSPENDED");
        if (suspended == null || !suspended)
        {
            request.setAttribute("SUSPENDED",Boolean.TRUE);
            AsyncContext ctx;
            if (originalReqResp)
            {
                // Use Original Request & Response
                ctx = request.startAsync();
            }
            else
            {
                // Pass Request & Response
                ctx = request.startAsync(request,response);
            }
            ctx.setTimeout(0);
            scheduler.schedule(new DispatchBack(ctx),500,TimeUnit.MILLISECONDS);
        }
        else
        {
            String fileName = request.getServletPath();
            byte[] dataBytes = loadContentFileBytes(fileName);

            response.setContentLength(dataBytes.length);

            ServletOutputStream out = response.getOutputStream();

            if (fileName.endsWith("txt"))
                response.setContentType("text/plain");
            else if (fileName.endsWith("mp3"))
                response.setContentType("audio/mpeg");
            response.setHeader("ETag","W/etag-" + fileName);

            out.write(dataBytes);
        }
    }
