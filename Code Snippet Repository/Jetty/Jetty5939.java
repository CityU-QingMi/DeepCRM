    public void visitLocaleEncodingList(WebAppContext context, Descriptor descriptor, XmlParser.Node node)
    {
        Iterator<XmlParser.Node> iter = node.iterator("locale-encoding-mapping");
        while (iter.hasNext())
        {
            XmlParser.Node mapping = iter.next();
            String locale = mapping.getString("locale", false, true);
            String encoding = mapping.getString("encoding", false, true);

            if (encoding != null)
            {
                switch (context.getMetaData().getOrigin("locale-encoding."+locale))
                {
                    case NotSet:
                    {
                        //no mapping for the locale yet, so set it
                        context.addLocaleEncoding(locale, encoding);
                        context.getMetaData().setOrigin("locale-encoding."+locale, descriptor);
                        break;
                    }
                    case WebXml:
                    case WebDefaults:
                    case WebOverride:
                    {
                        //a value was set in a web descriptor, only allow another web descriptor to change it (web-default/web-override)
                        if (!(descriptor instanceof FragmentDescriptor))
                        {
                            context.addLocaleEncoding(locale, encoding);
                            context.getMetaData().setOrigin("locale-encoding."+locale, descriptor);
                        }
                        break;
                    }
                    case WebFragment:
                    {
                        //a value was set by a web-fragment, all fragments must have the same value
                        if (!encoding.equals(context.getLocaleEncoding(locale)))
                            throw new IllegalStateException("Conflicting loacle-encoding mapping for locale "+locale+" in "+descriptor.getResource());
                        break;
                    }
                    default:
                        LOG.warn(new Throwable()); // TODO throw ISE?
                }
            }
        }
    }
