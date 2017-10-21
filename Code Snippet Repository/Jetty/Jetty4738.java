    protected void init()
    {
        Method[] methods = _pojoClass.getMethods();
        for (int i=0;i<methods.length;i++)
        {
            Method m=methods[i];
            if (!Modifier.isStatic(m.getModifiers()) && m.getDeclaringClass()!=Object.class)
            {
                String name=m.getName();
                switch(m.getParameterCount())
                {
                    case 0:
                        
                        if(m.getReturnType()!=null)
                        {
                            if (name.startsWith("is") && name.length()>2)
                                name=name.substring(2,3).toLowerCase(Locale.ENGLISH)+name.substring(3);
                            else if (name.startsWith("get") && name.length()>3)
                                name=name.substring(3,4).toLowerCase(Locale.ENGLISH)+name.substring(4);
                            else 
                                break;
                            if(includeField(name, m))
                                addGetter(name, m);
                        }
                        break;
                    case 1:
                        if (name.startsWith("set") && name.length()>3)
                        {
                            name=name.substring(3,4).toLowerCase(Locale.ENGLISH)+name.substring(4);
                            if(includeField(name, m))
                                addSetter(name, m);
                        }
                        break;                
                }
            }
        }
    }
