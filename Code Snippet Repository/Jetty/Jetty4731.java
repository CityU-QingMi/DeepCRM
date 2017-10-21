    protected Object convertTo(Class type, Map map)
    {
        if (type != null && Convertible.class.isAssignableFrom(type))
        {
            try
            {
                Convertible conv = (Convertible)type.newInstance();
                conv.fromJSON(map);
                return conv;
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }

        Convertor convertor = getConvertor(type);
        if (convertor != null)
        {
            return convertor.fromJSON(map);
        }
        return map;
    }
