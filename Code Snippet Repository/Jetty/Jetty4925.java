    public static Object toArray(Object list,Class<?> clazz)
    {
        if (list==null)
            return Array.newInstance(clazz,0);
        
        if (list instanceof List)
        {
            List<?> l = (List<?>)list;
            if (clazz.isPrimitive())
            {
                Object a = Array.newInstance(clazz,l.size());
                for (int i=0;i<l.size();i++)
                    Array.set(a,i,l.get(i));
                return a;
            }
            return l.toArray((Object[])Array.newInstance(clazz,l.size()));
            
        }
        
        Object a = Array.newInstance(clazz,1);
        Array.set(a,0,list);
        return a;
    }
