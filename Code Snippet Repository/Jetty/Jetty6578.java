    public List<ExtensionConfig> getNegotiatedExtensions()
    {
        List<ExtensionConfig> ret = new ArrayList<>();
        if (extensions == null)
        {
            return ret;
        }

        for (Extension ext : extensions)
        {
            if (ext.getName().charAt(0) == '@')
            {
                // special, internal-only extensions, not present on negotiation level
                continue;
            }
            ret.add(ext.getConfig());
        }
        return ret;
    }
