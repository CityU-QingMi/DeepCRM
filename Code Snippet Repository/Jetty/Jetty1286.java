    @Override
    public boolean equals(Object o)
    {
        if (o==this)
            return true;
        if (!(o instanceof HttpField))
            return false;
        HttpField field=(HttpField)o;
        if (_header!=field.getHeader())
            return false;
        if (!_name.equalsIgnoreCase(field.getName()))
            return false;
        if (_value==null && field.getValue()!=null)
            return false;
        return Objects.equals(_value,field.getValue());
    }
