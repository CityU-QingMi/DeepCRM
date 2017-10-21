    public Object invoke(String name, Object[] params, String[] signature) throws MBeanException, ReflectionException
    {
        if (LOG.isDebugEnabled())
            LOG.debug("ObjectMBean:invoke " + name);

        String methodKey = name + "(";
        if (signature != null)
            for (int i = 0; i < signature.length; i++)
                methodKey += (i > 0 ? "," : "") + signature[i];
        methodKey += ")";

        ClassLoader old_loader=Thread.currentThread().getContextClassLoader();
        try
        {
            Thread.currentThread().setContextClassLoader(_loader);
            Method method = (Method) _methods.get(methodKey);
            if (method == null)
                throw new NoSuchMethodException(methodKey);

            Object o = _managed;

            if (method.getDeclaringClass().isInstance(this))
            {
                o = this;
            }
            return method.invoke(o, params);
        }
        catch (NoSuchMethodException e)
        {
            LOG.warn(Log.EXCEPTION, e);
            throw new ReflectionException(e);
        }
        catch (IllegalAccessException e)
        {
            LOG.warn(Log.EXCEPTION, e);
            throw new MBeanException(e);
        }
        catch (InvocationTargetException e)
        {
            LOG.warn(Log.EXCEPTION, e);
            throw new ReflectionException(new Exception(e.getCause()));
        }
        finally
        {
            Thread.currentThread().setContextClassLoader(old_loader);
        }
    }
