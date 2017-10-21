    private static boolean isAnnotationPresentOnInterfaces(Class<?> clazz, Class<? extends Annotation> a) {
        for(Class<?> i : clazz.getInterfaces()) {
            if( i.isAnnotationPresent(a) )
                return true;
            if(isAnnotationPresentOnInterfaces(i, a))
                return true;
        }
        
        return false;
    }
