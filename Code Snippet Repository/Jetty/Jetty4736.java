    public void toJSON(Object obj, Output out)
    {
        if (_fromJSON)
        {
            out.addClass(obj.getClass());
            out.add("value",((Enum)obj).name());
        }
        else
        {
            out.add(((Enum)obj).name());
        }
    }
