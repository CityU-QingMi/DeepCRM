    private static boolean isTypeCompatible( Class<?> type, Object value )
    {
        if ( type.isInstance( value ) )
        {
            return true;
        }
        // likely Boolean -> boolean, Short -> int etc. conversions, it's not the problem case we try to avoid
        return ( ( type.isPrimitive() || type.getName().startsWith( "java.lang." ) )
                        && value.getClass().getName().startsWith( "java.lang." ) );
    }
