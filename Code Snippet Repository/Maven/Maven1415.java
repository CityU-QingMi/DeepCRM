            @Override
            void doInterpolate( Object target, InterpolateObjectAction ctx )
                throws IllegalAccessException
            {
                @SuppressWarnings( "unchecked" ) Map<Object, Object> m = (Map<Object, Object>) field.get( target );
                if ( m == null || m.isEmpty() )
                {
                    return;
                }

                for ( Map.Entry<Object, Object> entry : m.entrySet() )
                {
                    Object value = entry.getValue();

                    if ( value == null )
                    {
                        continue;
                    }

                    if ( String.class == value.getClass() )
                    {
                        String interpolated = ctx.interpolate( (String) value );

                        if ( !interpolated.equals( value ) )
                        {
                            try
                            {
                                entry.setValue( interpolated );
                            }
                            catch ( UnsupportedOperationException ignore )
                            {
                                // nop
                            }
                        }
                    }
                    else if ( value.getClass().isArray() )
                    {
                        evaluateArray( value, ctx );
                    }
                    else
                    {
                        ctx.interpolationTargets.add( value );
                    }
                }
            }
