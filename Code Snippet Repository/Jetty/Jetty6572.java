    @Override
    protected void doStart() throws Exception
    {
        super.doStart();

        // Wire up Extensions
        if ((extensions != null) && (extensions.size() > 0))
        {
            ListIterator<Extension> exts = extensions.listIterator();

            // Connect outgoings
            while (exts.hasNext())
            {
                Extension ext = exts.next();
                ext.setNextOutgoingFrames(nextOutgoing);
                nextOutgoing = ext;
                
                if (ext instanceof LifeCycle)
                {
                    addBean(ext,true);
                }
            }

            // Connect incomings
            while (exts.hasPrevious())
            {
                Extension ext = exts.previous();
                ext.setNextIncomingFrames(nextIncoming);
                nextIncoming = ext;
            }
        }
    }
