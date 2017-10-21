    public List<String> getDependentMavenWarExcludes()
    {
        if (_dependentMavenWarExcludes == null)
        {
            getPlugin();

            if (_plugin == null)
                return null;

            Xpp3Dom node = (Xpp3Dom)_plugin.getConfiguration();
            if (node == null)
                return null;

            node = node.getChild("dependentWarExcludes");
            if (node == null)
                return null;
            String val = node.getValue();
            _dependentMavenWarExcludes = StringUtil.csvSplit(null,val,0,val.length());
        }
        return _dependentMavenWarExcludes;
    }
