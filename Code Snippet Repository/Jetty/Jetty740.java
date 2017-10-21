    private void requestAppGoal(AppEntry appentry, String nodeName)
    {
        Node destinationNode = _lifecycle.getNodeByName(nodeName);
        if (destinationNode == null)
        {
            throw new IllegalStateException("Node not present in Deployment Manager: " + nodeName);
        }
        // Compute lifecycle steps
        Path path = _lifecycle.getPath(appentry.lifecyleNode,destinationNode);
        if (path.isEmpty())
        {
            // nothing to do. already there.
            return;
        }

        // Execute each Node binding.  Stopping at any thrown exception.
        try
        {
            Iterator<Node> it = path.getNodes().iterator();
            if (it.hasNext()) // Any entries?
            {
                // The first entry in the path is always the start node
                // We don't want to run bindings on that entry (again)
                it.next(); // skip first entry
                while (it.hasNext())
                {
                    Node node = it.next();
                    LOG.debug("Executing Node {}",node);
                    _lifecycle.runBindings(node,appentry.app,this);
                    appentry.setLifeCycleNode(node);
                }
            }
        }
        catch (Throwable t)
        {
            LOG.warn("Unable to reach node goal: " + nodeName,t);
        }
    }
