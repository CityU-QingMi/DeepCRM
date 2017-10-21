    public void introspect (Class<?> clazz)
    {
        if (_handlers == null)
            return;
        if (clazz == null)
            return;
        
        for (IntrospectableAnnotationHandler handler:_handlers)
        {
            try
            {
                handler.handle(clazz);
            }
            catch (RuntimeException e)
            {
                throw e;
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
     
    }
