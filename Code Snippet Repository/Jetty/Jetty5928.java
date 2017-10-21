    public void visitTagLib(WebAppContext context, Descriptor descriptor, XmlParser.Node node)
    {
        //Additive across web.xml and web-fragment.xml
        String uri = node.getString("taglib-uri", false, true);
        String location = node.getString("taglib-location", false, true);

        context.setResourceAlias(uri, location);

        JspConfig config = (JspConfig)context.getServletContext().getJspConfigDescriptor();
        if (config == null)
        {
            config = new JspConfig();
            context.getServletContext().setJspConfigDescriptor(config);
        }

        TagLib tl = new TagLib();
        tl.setTaglibLocation(location);
        tl.setTaglibURI(uri);
        config.addTaglibDescriptor(tl);
    }
