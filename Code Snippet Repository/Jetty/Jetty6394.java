    public ExtensionFactory()
    {
        availableExtensions = new HashMap<>();
        for (Extension ext : extensionLoader)
        {
            if (ext != null)
            {
                availableExtensions.put(ext.getName(),ext.getClass());
            }
        }
    }
