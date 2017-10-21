    public void visitWelcomeFileList(WebAppContext context, Descriptor descriptor, XmlParser.Node node)
    {
        switch (context.getMetaData().getOrigin("welcome-file-list"))
        {
            case NotSet:
            {
                context.getMetaData().setOrigin("welcome-file-list", descriptor);
                addWelcomeFiles(context,node);
                break;
            }
            case WebXml:
            {
                //web.xml set the welcome-file-list, all other descriptors then just merge in
                addWelcomeFiles(context,node);
                break;
            }
            case WebDefaults:
            {
                //if web-defaults set the welcome-file-list first and
                //we're processing web.xml then reset the welcome-file-list
                if (!(descriptor instanceof DefaultsDescriptor) && !(descriptor instanceof OverrideDescriptor) && !(descriptor instanceof FragmentDescriptor))
                {
                    context.setWelcomeFiles(new String[0]);
                }
                addWelcomeFiles(context,node);
                break;
            }
            case WebOverride:
            {
                //web-override set the list, all other descriptors just merge in
                addWelcomeFiles(context,node);
                break;
            }
            case WebFragment:
            {
                //A web-fragment first set the welcome-file-list. Other descriptors just add.
                addWelcomeFiles(context,node);
                break;
            }
            default:
                LOG.warn(new Throwable()); // TODO throw ISE?
        }
    }
