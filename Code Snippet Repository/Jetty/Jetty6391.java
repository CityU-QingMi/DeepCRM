    public static String toHeaderValue(List<ExtensionConfig> configs)
    {
        if ((configs == null) || (configs.isEmpty()))
        {
            return null;
        }
        StringBuilder parameters = new StringBuilder();
        boolean needsDelim = false;
        for (ExtensionConfig ext : configs)
        {
            if (needsDelim)
            {
                parameters.append(", ");
            }
            parameters.append(ext.getParameterizedName());
            needsDelim = true;
        }
        return parameters.toString();
    }
