    public void testPluginOrderAfterMergingWithInjectedPlugins()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "plugin-injection-merge-order" );

        List<String> expected = new ArrayList<>();
        expected.add( "maven-it-plugin-error" );
        expected.add( "maven-it-plugin-configuration" );
        expected.add( "maven-it-plugin-dependency-resolution" );
        expected.add( "maven-it-plugin-packaging" );
        expected.add( "maven-it-plugin-log-file" );
        expected.add( "maven-it-plugin-expression" );
        expected.add( "maven-it-plugin-fork" );
        expected.add( "maven-it-plugin-touch" );

        List<String> actual = new ArrayList<>();
        @SuppressWarnings( "unchecked" )
        List<Plugin> plugins = (List<Plugin>) pom.getValue( "build/plugins" );
        for ( Plugin plugin : plugins )
        {
            actual.add( plugin.getArtifactId() );
        }

        actual.retainAll( expected );

        assertEquals( actual, expected );
    }
