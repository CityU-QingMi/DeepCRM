            private boolean isQualifiedForInterpolation( Field field, Class<?> fieldType )
            {
                if ( Map.class.equals( fieldType ) && "locations".equals( field.getName() ) )
                {
                    return false;
                }

                //noinspection SimplifiableIfStatement
                if ( fieldType.isPrimitive() )
                {
                    return false;
                }

                return !"parent".equals( field.getName() );
            }
