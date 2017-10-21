    private List<ServletMapping>  getServletMappingsForServlet (String name)
    {
        ServletMapping[] allMappings = _context.getServletHandler().getServletMappings();
        if (allMappings == null)
            return Collections.emptyList();

        List<ServletMapping> mappings = new ArrayList<ServletMapping>();
        for (ServletMapping m:allMappings)
        {
            if (m.getServletName() != null && name.equals(m.getServletName()))
            {
                mappings.add(m);
            }
        }
        return mappings;
    }
