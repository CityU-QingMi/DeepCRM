    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append("ScopedInstance[");
        s.append(bean);
        s.append(',').append(creationalContext);
        s.append(']');
        return s.toString();
    }
