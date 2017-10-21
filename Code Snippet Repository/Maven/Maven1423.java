            void interpolate( Object target, InterpolateObjectAction interpolateObjectAction )
            {
                synchronized ( field )
                {
                    boolean isAccessible = field.isAccessible();
                    field.setAccessible( true );
                    try
                    {
                        doInterpolate( target, interpolateObjectAction );
                    }
                    catch ( IllegalArgumentException e )
                    {
                        interpolateObjectAction.problems.add(
                            new ModelProblemCollectorRequest( Severity.ERROR, Version.BASE ).setMessage(
                                "Failed to interpolate field3: " + field + " on class: "
                                    + field.getType().getName() ).setException(
                                e ) ); // TODO Not entirely the same message
                    }
                    catch ( IllegalAccessException e )
                    {
                        interpolateObjectAction.problems.add(
                            new ModelProblemCollectorRequest( Severity.ERROR, Version.BASE ).setMessage(
                                "Failed to interpolate field4: " + field + " on class: "
                                    + field.getType().getName() ).setException( e ) );
                    }
                    finally
                    {
                        field.setAccessible( isAccessible );
                    }
                }


            }
