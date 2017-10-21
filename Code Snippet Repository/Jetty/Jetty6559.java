    public void scanMethodAnnotations(T metadata, Class<?> pojo)
    {
        Class<?> clazz = pojo;

        while ((clazz != null) && Object.class.isAssignableFrom(clazz))
        {
            for (Method method : clazz.getDeclaredMethods())
            {
                Annotation annotations[] = method.getAnnotations();
                if ((annotations == null) || (annotations.length <= 0))
                {
                    continue; // skip
                }
                for (Annotation annotation : annotations)
                {
                    onMethodAnnotation(metadata,clazz,method,annotation);
                }
            }

            clazz = clazz.getSuperclass();
        }
    }
