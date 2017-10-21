    public List<String> getDependentMavenWarIncludes()
    {
        if (_dependentMavenWarIncludes == null)
        {
            getPlugin();

            if (_plugin == null)
                return null;

            Xpp3Dom node = (Xpp3Dom)_plugin.getConfiguration();
            if (node == null)
                return null;

            node = node.getChild("dependentWarIncludes");
            if (node == null)
                return null;
            String val = node.getValue();
            _dependentMavenWarIncludes = StringUtil.csvSplit(null,val,0,val.length());
        }
        return _dependentMavenWarIncludes;
    }
