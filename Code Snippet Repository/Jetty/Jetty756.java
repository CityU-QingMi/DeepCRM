    private void recursiveRemoveContext(HandlerCollection coll, ContextHandler context)
    {
        Handler children[] = coll.getHandlers();
        int originalCount = children.length;

        for (int i = 0, n = children.length; i < n; i++)
        {
            Handler child = children[i];
            LOG.debug("Child handler {}",child);
            if (child.equals(context))
            {
                LOG.debug("Removing handler {}",child);
                coll.removeHandler(child);
                child.destroy();
                if (LOG.isDebugEnabled())
                    LOG.debug("After removal: {} (originally {})",coll.getHandlers().length,originalCount);
            }
            else if (child instanceof HandlerCollection)
            {
                recursiveRemoveContext((HandlerCollection)child,context);
            }
        }
    }
