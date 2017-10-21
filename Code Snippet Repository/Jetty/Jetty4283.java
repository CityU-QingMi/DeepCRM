    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        assertThat("'filename' request attribute shouldn't be declared",request.getAttribute("filename"),nullValue());

        AsyncContext ctx = (AsyncContext)request.getAttribute(this.getClass().getName());
        assertThat("AsyncContext (shouldn't be in request attribute)", ctx, nullValue());
        
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
        String fileName = request.getServletPath();
        request.setAttribute("filename",fileName);
        ctx.addListener(this);
        ctx.setTimeout(20);
        
        // Setup indication of a redispatch (which this scenario shouldn't do)
        request.setAttribute(this.getClass().getName(),ctx);
    }
