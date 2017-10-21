    public Object fromJSON(Map object)
    {
        Map map=object;
        String clsName=(String)map.get("class");
        if (clsName!=null)
        {
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
                return convertor.fromJSON(object);
            }
        }
        return map;
    }
