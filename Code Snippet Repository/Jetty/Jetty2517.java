    private void outholder(XmlAppendable out, MetaData md, FilterHolder holder) throws IOException
    {
        if (LOG.isDebugEnabled())
            out.openTag("filter",Collections.singletonMap("source",holder.getSource().toString()));
        else
            out.openTag("filter");
        
        String n = holder.getName();
        out.tag("filter-name",n);

        String ot = n + ".filter.";
        
        if (holder instanceof FilterHolder)
        {
            out.tag("filter-class",origin(md,ot + "filter-class"),holder.getClassName());
            out.tag("async-supported",origin(md,ot + "async-supported"),holder.isAsyncSupported()?"true":"false");
        }
        
        for (String p : holder.getInitParameters().keySet())
        {
            out.openTag("init-param",origin(md,ot + "init-param." + p))
            .tag("param-name",p)
            .tag("param-value",holder.getInitParameter(p))
            .closeTag();
        }

        out.closeTag();
    }
