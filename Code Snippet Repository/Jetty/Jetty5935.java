    public void visitContextParam (WebAppContext context, Descriptor descriptor, XmlParser.Node node)
    {
        String name = node.getString("param-name", false, true);
        String value = node.getString("param-value", false, true);
        switch (context.getMetaData().getOrigin("context-param."+name))
        {
            case NotSet:
            {
                //just set it
                context.getInitParams().put(name, value);
                context.getMetaData().setOrigin("context-param."+name, descriptor);
                break;
            }
            case WebXml:
            case WebDefaults:
            case WebOverride:
            {
                //previously set by a web xml, allow other web xml files to override
                if (!(descriptor instanceof FragmentDescriptor))
                {
                    context.getInitParams().put(name, value);
                    context.getMetaData().setOrigin("context-param."+name, descriptor);
                }
                break;
            }
            case WebFragment:
            {
                //previously set by a web-fragment, this fragment's value must be the same
                if (descriptor instanceof FragmentDescriptor)
                {
                    if (!((String)context.getInitParams().get(name)).equals(value))
                        throw new IllegalStateException("Conflicting context-param "+name+"="+value+" in "+descriptor.getResource());
                }
                break;
            }
            default:
                LOG.warn(new Throwable()); // TODO throw ISE?
        }
        if (LOG.isDebugEnabled())
            LOG.debug("ContextParam: " + name + "=" + value);

    }
