    protected Object decodeValue(final Object valueToDecode) throws IOException, ClassNotFoundException
    {
        if (valueToDecode == null || valueToDecode instanceof Number || valueToDecode instanceof String || valueToDecode instanceof Boolean || valueToDecode instanceof Date)
        {
            return valueToDecode;
        }
        else if (valueToDecode instanceof byte[])
        {
            final byte[] decodeObject = (byte[])valueToDecode;
            final ByteArrayInputStream bais = new ByteArrayInputStream(decodeObject);
            final ClassLoadingObjectInputStream objectInputStream = new ClassLoadingObjectInputStream(bais);
            return objectInputStream.readUnshared();
        }
        else if (valueToDecode instanceof DBObject)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            for (String name : ((DBObject)valueToDecode).keySet())
            {
                String attr = decodeName(name);
                map.put(attr,decodeValue(((DBObject)valueToDecode).get(name)));
            }
            return map;
        }
        else
        {
            throw new IllegalStateException(valueToDecode.getClass().toString());
        }
    }
