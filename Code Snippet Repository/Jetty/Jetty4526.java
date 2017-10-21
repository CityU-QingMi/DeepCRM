    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append("Modules[");
        str.append("count=").append(_modules.size());
        str.append(",<");
        final AtomicBoolean delim = new AtomicBoolean(false);
        _modules.forEach(m->
        {
            if (delim.get())
                str.append(',');
            str.append(m.getName());
            delim.set(true);
        });
        str.append(">");
        str.append("]");
        return str.toString();
    }
