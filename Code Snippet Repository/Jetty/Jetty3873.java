    @Override
    public void init() throws ServletException
    {
        String jsp_name = "jsp";
        ServletMapping servlet_mapping =_servletHandler.getServletMapping("*.jsp");
        if (servlet_mapping!=null)
        {
            _starJspMapped=true;

            //now find the jsp servlet, ignoring the mapping that is for ourself
            ServletMapping[] mappings = _servletHandler.getServletMappings();
            for (ServletMapping m:mappings)
            {
                String[] paths = m.getPathSpecs();
                if (paths!=null)
                {
                    for (String path:paths)
                    {
                        if ("*.jsp".equals(path) && !NAME.equals(m.getServletName()))
                            servlet_mapping = m;
                    }
                }
            }

            jsp_name=servlet_mapping.getServletName();
        }
        _jspServlet=_servletHandler.getServlet(jsp_name);

        String dft_name="default";
        ServletMapping default_mapping=_servletHandler.getServletMapping("/");
        if (default_mapping!=null)
            dft_name=default_mapping.getServletName();
        _dftServlet=_servletHandler.getServlet(dft_name);
    }
