    public void testNestedEnter()
        throws Exception
    {
        MojoExecutionScope scope = new MojoExecutionScope();

        scope.enter();

        Object o1 = new Object();
        scope.seed( Object.class, o1 );
        assertSame( o1, scope.scope( Key.get( Object.class ), null ).get() );

        scope.enter();
        Object o2 = new Object();
        scope.seed( Object.class, o2 );
        assertSame( o2, scope.scope( Key.get( Object.class ), null ).get() );

        scope.exit();
        assertSame( o1, scope.scope( Key.get( Object.class ), null ).get() );

        scope.exit();

        try
        {
            scope.exit();
            fail();
        }
        catch ( IllegalStateException expected )
        {
        }
    }
