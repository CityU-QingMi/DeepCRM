    public void visitServletMapping(WebAppContext context, Descriptor descriptor, XmlParser.Node node)
    {
        //Servlet Spec 3.0, p74
        //servlet-mappings are always additive, whether from web xml descriptors (web.xml/web-default.xml/web-override.xml) or web-fragments.
        //Maintenance update 3.0a to spec:
        //  Updated 8.2.3.g.v to say <servlet-mapping> elements are additive across web-fragments.
        //  <servlet-mapping> declared in web.xml overrides the mapping for the servlet specified in the web-fragment.xml

        String servlet_name = node.getString("servlet-name", false, true);
        switch (context.getMetaData().getOrigin(servlet_name+".servlet.mappings"))
        {
            case NotSet:
            {
                //no servlet mappings
                context.getMetaData().setOrigin(servlet_name+".servlet.mappings", descriptor);
                addServletMapping(servlet_name, node, context, descriptor);               
                break;
            }
            case WebDefaults:
            case WebXml:
            case WebOverride:
            {
                //previously set by a web xml descriptor, if we're parsing another web xml descriptor allow override
                //otherwise just ignore it as web.xml takes precedence (pg 8-81 5.g.vi)
                if (!(descriptor instanceof FragmentDescriptor))
                {
                   addServletMapping(servlet_name, node, context, descriptor);
                }
                break;
            }
            case WebFragment:
            {
                //mappings previously set by another web-fragment, so merge in this web-fragment's mappings
                addServletMapping(servlet_name, node, context, descriptor);
                break;
            }
            default:
                LOG.warn(new Throwable()); // TODO throw ISE?
        }
    }
