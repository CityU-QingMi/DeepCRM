            @Override
            void doInterpolate( Object target, InterpolateObjectAction ctx )
                throws IllegalAccessException
            {
                String value = (String) field.get( target );
                if ( value == null )
                {
                    return;
                }

                String interpolated = ctx.interpolate( value );

                if ( !interpolated.equals( value ) )
                {
                    field.set( target, interpolated );
                }
            }
