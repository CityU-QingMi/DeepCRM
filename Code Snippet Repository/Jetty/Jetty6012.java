    private static Field getURLStreamHandlerFactoryField()
    {
        Optional<Field> optFactoryField = Arrays.stream(URL.class.getDeclaredFields())
                .filter((f) -> Modifier.isStatic(f.getModifiers()) &&
                               f.getType().equals(URLStreamHandlerFactory.class))
                .findFirst();
        
        if(optFactoryField.isPresent())
            return optFactoryField.get();
        
        throw new RuntimeException( "Cannot find URLStreamHandlerFactory field in " + URL.class.getName() );
    }
