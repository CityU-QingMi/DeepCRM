    public List<OverlayConfig> getMavenWarOverlayConfigs ()
    {
        if (_overlayConfigs == null)
        {
            getPlugin();

            if (_plugin == null)
                return Collections.emptyList();

            getDependentMavenWarIncludes();
            getDependentMavenWarExcludes();

            Xpp3Dom node = (Xpp3Dom)_plugin.getConfiguration();
            if (node == null)
                return Collections.emptyList();

            node = node.getChild("overlays");
            if (node == null)
                return Collections.emptyList();

            Xpp3Dom[] nodes = node.getChildren("overlay");
            if (nodes == null)
                return Collections.emptyList();

            _overlayConfigs = new ArrayList<OverlayConfig>();
            for (int i=0;i<nodes.length;i++)
            {
                OverlayConfig overlayConfig = new OverlayConfig(nodes[i], _dependentMavenWarIncludes, _dependentMavenWarExcludes);
                _overlayConfigs.add(overlayConfig);
            }
        }

        return _overlayConfigs;
    }
