    public void visitServletMapping(WebAppContext context, Descriptor descriptor, XmlParser.Node node)
    {
        String servletName = node.getString("servlet-name", false, true);
        ServletMapping mapping = null;
        ServletMapping[] mappings = context.getServletHandler().getServletMappings();

        if (mappings != null)
        {
            for (ServletMapping m:mappings)
            {
                if (servletName.equals(m.getServletName()))
                {
                    mapping = m;
                    break;
                }
            }
        }
        
        if (mapping != null && _originAttributeName != null)
        {
            String origin = node.getAttribute(_originAttributeName);
            if (!StringUtil.isBlank(origin) && origin.startsWith(DefaultsDescriptor.class.getSimpleName()))
                mapping.setDefault(true);
        }
    }
