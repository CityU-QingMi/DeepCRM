    public String asString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(getName()).append("=").append(getValue());
        if (getDomain() != null)
            builder.append(";$Domain=").append(getDomain());
        if (getPath() != null)
            builder.append(";$Path=").append(getPath());
        return builder.toString();
    }
