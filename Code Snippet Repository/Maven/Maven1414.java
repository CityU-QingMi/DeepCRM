            @Override
            void doInterpolate( Object target, InterpolateObjectAction ctx )
                throws IllegalAccessException
            {
                @SuppressWarnings( "unchecked" ) List<Object> c = (List<Object>) field.get( target );
                if ( c == null )
                {
                    return;
                }

                int size = c.size();
                Object value;
                for ( int i = 0; i < size; i++ )
                {

                    value = c.get( i );

                    if ( value != null )
                    {
                        if ( String.class == value.getClass() )
                        {
                            String interpolated = ctx.interpolate( (String) value );

                            if ( !interpolated.equals( value ) )
                            {
                                try
                                {
                                    c.set( i, interpolated );
                                }
                                catch ( UnsupportedOperationException e )
                                {
                                    return;
                                }
                            }
                        }
                        else
                        {
                            if ( value.getClass().isArray() )
                            {
                                evaluateArray( value, ctx );
                            }
                            else
                            {
                                ctx.interpolationTargets.add( value );
                            }
                        }
                    }
                }
            }
