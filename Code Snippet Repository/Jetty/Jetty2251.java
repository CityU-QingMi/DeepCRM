    public void setTarget (Class<?> clazz, String target, Class<?> resourceType)
    {
        _targetClass = clazz;
        _resourceClass = resourceType;

        //first look for a javabeans style setter matching the targetName
        String setter = "set"+target.substring(0,1).toUpperCase(Locale.ENGLISH)+target.substring(1);
        try
        {
            LOG.debug("Looking for method for setter: "+setter+" with arg "+_resourceClass);
            _target = IntrospectionUtil.findMethod(clazz, setter, new Class[] {_resourceClass}, true, false);
            _targetClass = clazz;
            _paramClass = _resourceClass;
        }
        catch (NoSuchMethodException me)
        {
            //try as a field
            try
            {
                _target = IntrospectionUtil.findField(clazz, target, resourceType, true, false);
                _targetClass = clazz;
            }
            catch (NoSuchFieldException fe)
            {
                throw new IllegalArgumentException("No such field or method "+target+" on class "+_targetClass);
            }
        }

    }
