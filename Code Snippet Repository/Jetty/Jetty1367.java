    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        MappedResource<?> other = (MappedResource<?>)obj;
        if (pathSpec == null)
        {
            if (other.pathSpec != null)
            {
                return false;
            }
        }
        else if (!pathSpec.equals(other.pathSpec))
        {
            return false;
        }
        return true;
    }
