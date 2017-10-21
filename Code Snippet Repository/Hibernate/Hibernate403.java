    public boolean isInstance(Object object) {
        try {
            return targetClass.isInstance( object );
        }
        catch( Throwable t ) {
            throw new HibernateException(
                "could not get handle to entity as interface : " + t
            );
        }
    }
