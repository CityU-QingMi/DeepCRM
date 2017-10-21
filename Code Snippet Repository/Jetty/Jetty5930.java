    public void visitFilterMapping(WebAppContext context, Descriptor descriptor, XmlParser.Node node)
    {
        //Servlet Spec 3.0, p74
        //filter-mappings are always additive, whether from web xml descriptors (web.xml/web-default.xml/web-override.xml) or web-fragments.
        //Maintenance update 3.0a to spec:
        //  Updated 8.2.3.g.v to say <servlet-mapping> elements are additive across web-fragments.
        String filter_name = node.getString("filter-name", false, true);
        switch (context.getMetaData().getOrigin(filter_name+".filter.mappings"))
        {
            case NotSet:
            {
                //no filtermappings for this filter yet defined
                context.getMetaData().setOrigin(filter_name+".filter.mappings", descriptor);
                addFilterMapping(filter_name, node, context, descriptor);
                break;
            }
            case WebDefaults:
            case WebOverride:
            case WebXml:
            {
                //filter mappings defined in a web xml file. If we're processing a fragment, we ignore filter mappings.
                if (!(descriptor instanceof FragmentDescriptor))
                {
                   addFilterMapping(filter_name, node, context, descriptor);
                }
                break;
            }
            case WebFragment:
            {
                //filter mappings first defined in a web-fragment, allow other fragments to add
                addFilterMapping(filter_name, node, context, descriptor);
                break;
            }
            default:
                LOG.warn(new Throwable()); // TODO throw ISE?
        }
    }
