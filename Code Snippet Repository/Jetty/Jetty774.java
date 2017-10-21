    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node)obj;
        if (_name == null)
        {
            if (other._name != null)
                return false;
        }
        else if (!_name.equals(other._name))
            return false;
        return true;
    }
