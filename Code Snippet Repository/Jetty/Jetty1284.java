    public boolean isSameName(HttpField field)
    {
        if (field==null)
            return false;
        if (field==this)
            return true;
        if (_header!=null && _header==field.getHeader())
            return true;
        if (_name.equalsIgnoreCase(field.getName()))
            return true;
        return false;
    }
