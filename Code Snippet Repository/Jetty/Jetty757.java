    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Edge other = (Edge)obj;
        if (_from == null)
        {
            if (other._from != null)
                return false;
        }
        else if (!_from.equals(other._from))
            return false;
        if (_to == null)
        {
            if (other._to != null)
                return false;
        }
        else if (!_to.equals(other._to))
            return false;
        return true;
    }
