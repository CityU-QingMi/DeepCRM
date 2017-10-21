    public void setAttribute(Attribute attr) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException
    {
        if (attr == null)
            return;

        if (LOG.isDebugEnabled())
            LOG.debug("setAttribute " + _managed + ":" +attr.getName() + "=" + attr.getValue());
        Method setter = (Method) _setters.get(attr.getName());
        if (setter == null)
            throw new AttributeNotFoundException(attr.getName());
        try
        {
            Object o = _managed;
            if (setter.getDeclaringClass().isInstance(this))
                o = this;

            // get the value
            Object value = attr.getValue();

            // convert from ObjectName if need be
            if (value!=null && _convert.contains(attr.getName()))
            {
                if (value.getClass().isArray())
                {
                    Class<?> t=setter.getParameterTypes()[0].getComponentType();
                    Object na = Array.newInstance(t,Array.getLength(value));
                    for (int i=Array.getLength(value);i-->0;)
                        Array.set(na, i, _mbeanContainer.findBean((ObjectName)Array.get(value, i)));
                    value=na;
                }
                else
                    value=_mbeanContainer.findBean((ObjectName)value);
            }

            // do the setting
            setter.invoke(o, new Object[]{ value });
        }
        catch (IllegalAccessException e)
        {
            LOG.warn(Log.EXCEPTION, e);
            throw new AttributeNotFoundException(e.toString());
        }
        catch (InvocationTargetException e)
        {
            LOG.warn(Log.EXCEPTION, e);
            throw new ReflectionException(new Exception(e.getCause()));
        }
    }
