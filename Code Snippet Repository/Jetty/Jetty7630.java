    @Override
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);

        if (config.getInitParameter("unavailable")!=null && !fixed)
        {

            fixed=true;
            throw new UnavailableException("Unavailable test",Integer.parseInt(config.getInitParameter("unavailable")));
        }

        _timer=new Timer(true);
    }
