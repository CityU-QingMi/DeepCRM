    public void parse (Set<? extends Handler> handlers, Class<?> clazz, boolean visitSuperClasses) throws Exception
    {
        Class<?> cz = clazz;
        while (cz != Object.class)
        {
            String nameAsResource = cz.getName().replace('.', '/')+".class";
            URL resource = Loader.getResource(nameAsResource);
            if (resource!= null)
            {
                Resource r = Resource.newResource(resource);
                addParsedClass(clazz.getName(), r);
                try (InputStream is =  r.getInputStream())
                {
                    scanClass(handlers, null, is);
                }
            }

            if (visitSuperClasses)
                cz = cz.getSuperclass();
            else
                break;
        }
    }
