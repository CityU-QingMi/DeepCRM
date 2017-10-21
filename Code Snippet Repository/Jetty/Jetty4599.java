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
        DirConfigSource other = (DirConfigSource)obj;
        if (dir == null)
        {
            if (other.dir != null)
            {
                return false;
            }
        }
        else if (!dir.equals(other.dir))
        {
            return false;
        }
        return true;
    }
