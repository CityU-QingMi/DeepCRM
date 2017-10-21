    public void toJSON(Object obj, Output out)
    {
        String clsName=obj.getClass().getName();
        Convertor convertor=_json.getConvertorFor(clsName);
        if (convertor==null)
        {
            try
            {
                Class cls=Loader.loadClass(clsName);
                convertor=new JSONPojoConvertor(cls,_fromJson);
                _json.addConvertorFor(clsName, convertor);
             }
            catch (ClassNotFoundException e)
            {
                JSON.LOG.warn(e);
            }
        }
        if (convertor!=null)
        {
            convertor.toJSON(obj, out);
        }
    }
