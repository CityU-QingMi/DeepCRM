    @Override
    public void init() throws ServletException
    {
        try
        {
            WebSocketPolicy policy = new WebSocketPolicy(WebSocketBehavior.SERVER);

            String max = getInitParameter("maxIdleTime");
            if (max != null)
            {
                policy.setIdleTimeout(Long.parseLong(max));
            }

            max = getInitParameter("maxTextMessageSize");
            if (max != null)
            {
                policy.setMaxTextMessageSize(Integer.parseInt(max));
            }

            max = getInitParameter("maxBinaryMessageSize");
            if (max != null)
            {
                policy.setMaxBinaryMessageSize(Integer.parseInt(max));
            }

            max = getInitParameter("inputBufferSize");
            if (max != null)
            {
                policy.setInputBufferSize(Integer.parseInt(max));
            }
    
            ServletContext ctx = getServletContext();
            factory = WebSocketServletFactory.Loader.load(ctx, policy);

            configure(factory);
            
            factory.start();
            
            ctx.setAttribute(WebSocketServletFactory.class.getName(),factory);
        }
        catch (Exception x)
        {
            throw new ServletException(x);
        }
    }
