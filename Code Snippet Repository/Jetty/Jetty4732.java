    public Object fromJSON(Map object)
    {
        try
        {
            Collection result = (Collection)Loader.loadClass((String)object.get("class")).newInstance();
            Collections.addAll(result, (Object[])object.get("list"));
            return result;
        }
        catch (Exception x)
        {
            if (x instanceof RuntimeException)
                throw (RuntimeException)x;
            throw new RuntimeException(x);
        }
    }
