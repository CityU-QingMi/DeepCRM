    public ServletMapping getServletMapping(String pathSpec)
    {
        if (pathSpec == null || _servletMappings == null)
            return null;

        ServletMapping mapping = null;
        for (int i=0; i<_servletMappings.length && mapping == null; i++)
        {
            ServletMapping m = _servletMappings[i];
            if (m.getPathSpecs() != null)
            {
                for (String p:m.getPathSpecs())
                {
                    if (pathSpec.equals(p))
                    {
                        mapping = m;
                        break;
                    }
                }
            }
        }
        return mapping;
    }
