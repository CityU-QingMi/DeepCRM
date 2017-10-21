    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("StartArgs [enabledModules=");
        builder.append(modules);
        builder.append(", xmlRefs=");
        builder.append(xmlRefs);
        builder.append(", properties=");
        builder.append(properties);
        builder.append(", jvmArgs=");
        builder.append(jvmArgs);
        builder.append("]");
        return builder.toString();
    }
