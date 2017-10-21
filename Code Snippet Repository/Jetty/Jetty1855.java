    protected boolean isAnnotationPresent(Class<?> clazz, Class<? extends Annotation> annotation)
    {
        Class<?> test = clazz;
        
        while (test != null )
        {  
            if ( test.isAnnotationPresent(annotation))
            {
                return true;
            }
            else
            {
                test = test.getSuperclass();
            }
        }
        return false;
    }
