    public Object fromJSON(Map object)
    {        
        Object obj = null;
        try
        {
            obj = _pojoClass.newInstance();
        }
        catch(Exception e)
        {
            // TODO return Map instead?
            throw new RuntimeException(e);
        }
        
        setProps(obj, object);
        return obj;
    }
