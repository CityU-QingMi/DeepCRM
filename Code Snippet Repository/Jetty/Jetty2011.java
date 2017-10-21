    public String getMavenWarOverlayConfigAsString ()
    {
        getPlugin();

        if (_plugin == null)
            return "";
        
        Xpp3Dom node = (Xpp3Dom)_plugin.getConfiguration();
        if (node == null)
            return "";
        return node.toString();
    }
