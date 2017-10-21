    public void process(WebAppContext context, Descriptor descriptor)
    throws Exception
    {
        if (descriptor == null)
            return;

        start(context,descriptor);

        XmlParser.Node root = descriptor.getRoot();
        Iterator<?> iter = root.iterator();
        XmlParser.Node node = null;
        while (iter.hasNext())
        {
            Object o = iter.next();
            if (!(o instanceof XmlParser.Node)) continue;
            node = (XmlParser.Node) o;
            visit(context, descriptor, node);
        }

        end(context,descriptor);
    }
