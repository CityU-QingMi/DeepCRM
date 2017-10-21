    public void init()
    {
        ServletContext config=getServletContext();
        _contextHandler=((ContextHandler.Context)config).getContextHandler();

        Handler handler=_contextHandler.getHandler();
        while (handler!=null && !(handler instanceof ServletHandler) && (handler instanceof HandlerWrapper))
            handler=((HandlerWrapper)handler).getHandler();
        _servletHandler = (ServletHandler)handler;
        Enumeration<String> e = getInitParameterNames();
        while(e.hasMoreElements())
        {
            String param=e.nextElement();
            String value=getInitParameter(param);
            String lvalue=value.toLowerCase(Locale.ENGLISH);
            if ("nonContextServlets".equals(param))
            {
                _nonContextServlets=value.length()>0 && lvalue.startsWith("t");
            }
            if ("verbose".equals(param))
            {
                _verbose=value.length()>0 && lvalue.startsWith("t");
            }
            else
            {
                if (_parameters==null)
                    _parameters=new HashMap<String, String>();
                _parameters.put(param,value);
            }
        }
    }
