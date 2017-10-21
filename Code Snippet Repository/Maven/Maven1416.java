            @Override
            void doInterpolate( Object target, InterpolateObjectAction ctx )
                throws IllegalAccessException
            {
                Object value = field.get( target );
                if ( value != null )
                {
                    if ( isArray )
                    {
                        evaluateArray( value, ctx );
                    }
                    else
                    {
                        ctx.interpolationTargets.add( value );
                    }
                }
            }
