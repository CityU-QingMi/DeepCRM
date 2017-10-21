    protected Convertor getConvertor(Class forClass)
    {
        Class cls = forClass;
        Convertor convertor = _convertors.get(cls.getName());
        if (convertor == null && this != DEFAULT)
            convertor = DEFAULT.getConvertor(cls);

        while (convertor == null && cls != Object.class)
        {
            Class[] ifs = cls.getInterfaces();
            int i = 0;
            while (convertor == null && ifs != null && i < ifs.length)
                convertor = _convertors.get(ifs[i++].getName());
            if (convertor == null)
            {
                cls = cls.getSuperclass();
                convertor = _convertors.get(cls.getName());
            }
        }
        return convertor;
    }
