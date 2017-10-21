        private boolean isQualifiedForInterpolation( Field field, Class<?> fieldType )
        {
            if ( !PRIMITIVE_BY_CLASS.containsKey( fieldType ) )
            {
                PRIMITIVE_BY_CLASS.put( fieldType, fieldType.isPrimitive() );
            }

            if ( PRIMITIVE_BY_CLASS.get( fieldType ) )
            {
                return false;
            }

//            if ( fieldType.isPrimitive() )
//            {
//                return false;
//            }

            if ( "parent".equals( field.getName() ) )
            {
                return false;
            }

            return true;
        }
