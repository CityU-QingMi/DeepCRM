    public void visitMimeMapping(WebAppContext context, Descriptor descriptor, XmlParser.Node node)
    {
        String extension = node.getString("extension", false, true);
        if (extension != null && extension.startsWith("."))
            extension = extension.substring(1);
        String mimeType = node.getString("mime-type", false, true);
        if (extension != null)
        {
            switch (context.getMetaData().getOrigin("extension."+extension))
            {
                case NotSet:
                {
                    //no mime-type set for the extension yet
                    context.getMimeTypes().addMimeMapping(extension, mimeType);
                    context.getMetaData().setOrigin("extension."+extension, descriptor);
                    break;
                }
                case WebXml:
                case WebDefaults:
                case WebOverride:
                {
                    //a mime-type was set for the extension in a web xml, only allow web-default/web-override to change
                    if (!(descriptor instanceof FragmentDescriptor))
                    {
                        context.getMimeTypes().addMimeMapping(extension, mimeType);
                        context.getMetaData().setOrigin("extension."+extension, descriptor);
                    }
                    break;
                }
                case WebFragment:
                {
                    //a web-fragment set the value, all web-fragments must have the same value
                    if (!context.getMimeTypes().getMimeByExtension("."+extension).equals(mimeType))
                        throw new IllegalStateException("Conflicting mime-type "+mimeType+" for extension "+extension+" in "+descriptor.getResource());
                    break;
                }
                default:
                    LOG.warn(new Throwable()); // TODO throw ISE?
            }
        }
    }
