    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append(getName());
        char sep='{';
        if (isDynamic())
        {
            str.append(sep).append("dynamic");
            sep=',';
        }
        if (isEnabled())
        {
            str.append(sep).append("enabled");
            sep=',';
        }
        if (isTransitive())
        {
            str.append(sep).append("transitive");
            sep=',';
        }
        if (sep!='{')
            str.append('}');
        return str.toString();
    }
