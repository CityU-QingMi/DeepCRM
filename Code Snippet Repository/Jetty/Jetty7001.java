    private String getNegotiatedExtensionList(Session session)
    {
        StringBuilder actual = new StringBuilder();
        actual.append('[');

        boolean delim = false;
        for (ExtensionConfig ext : session.getUpgradeResponse().getExtensions())
        {
            if (delim)
                actual.append(", ");
            actual.append(ext.getName());
            delim = true;
        }
        actual.append(']');

        return actual.toString();
    }
