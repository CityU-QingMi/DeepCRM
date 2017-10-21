    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        AsyncContext ctx = (AsyncContext)request.getAttribute(AsyncContext.class.getName());
        if (ctx == null)
        {
            // First pass through
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
            ctx.addListener(this);
            ctx.setTimeout(200);
            request.setAttribute(AsyncContext.class.getName(),ctx);
        }
        else
        {
            // second pass through, as result of timeout -> dispatch
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
            // no need to call AsyncContext.complete() from here
            // in fact, it will cause an IllegalStateException if we do
            // ctx.complete();
        }
    }
