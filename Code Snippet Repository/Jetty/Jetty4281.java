    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        LOG.debug("doFilter() - {}", chain);
        AsyncContext ctx = (AsyncContext)request.getAttribute(MANIP_KEY);
        if (ctx == null)
        {
            LOG.debug("Initial pass through: {}", chain);
            ctx = request.startAsync();
            ctx.addListener(this);
            ctx.setTimeout(1000);
            LOG.debug("AsyncContext: {}", ctx);
            request.setAttribute(MANIP_KEY,ctx);
            return;
        }
        else
        {
            LOG.debug("Second pass through: {}", chain);
            chain.doFilter(request,response);
        }
    }
