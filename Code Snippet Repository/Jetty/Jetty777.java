    @ManagedOperation(value = "", impact = "")
    public Collection<String> getApps(@Name("nodeName") String nodeName)
    {
        Node node = _manager.getLifeCycle().getNodeByName(nodeName);
        if (node == null)
        {
            throw new IllegalArgumentException("Unable to find node [" + nodeName + "]");
        }

        List<String> ret = new ArrayList<>();
        for (DeploymentManager.AppEntry entry : _manager.getAppEntries())
        {
            if (entry.getLifecyleNode() == node)
            {
                ret.add(toRef(entry.getApp()));
            }
        }
        return ret;
    }
