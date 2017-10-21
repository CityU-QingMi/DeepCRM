    public void toJSON(Object obj, Output out)
    {
        try
        {
            Class c=obj.getClass();

            if (_fromJSON)
                out.addClass(obj.getClass());

            Method[] methods = obj.getClass().getMethods();

            for (int i=0;i<methods.length;i++)
            {
                Method m=methods[i];
                if (!Modifier.isStatic(m.getModifiers()) &&  
                        m.getParameterCount()==0 &&
                        m.getReturnType()!=null &&
                        m.getDeclaringClass()!=Object.class)
                {
                    String name=m.getName();
                    if (name.startsWith("is"))
                        name=name.substring(2,3).toLowerCase(Locale.ENGLISH)+name.substring(3);
                    else if (name.startsWith("get"))
                        name=name.substring(3,4).toLowerCase(Locale.ENGLISH)+name.substring(4);
                    else
                        continue;

                    if (includeField(name,obj,m))
                        out.add(name, m.invoke(obj,(Object[])null));
                }
            }
        } 
        catch (Throwable e)
        {
            throw new IllegalArgumentException(e);
        }
    }
