    public void testMissingRequiredStringArrayTypeParameter()
    {
        MojoDescriptor mojoDescriptor = new MojoDescriptor();
        mojoDescriptor.setGoal( "goal" );
        PluginDescriptor pluginDescriptor = new PluginDescriptor();
        pluginDescriptor.setGoalPrefix( "goalPrefix" );
        pluginDescriptor.setArtifactId( "artifactId" );
        mojoDescriptor.setPluginDescriptor( pluginDescriptor );

        Parameter parameter = new Parameter();
        parameter.setType( "java.lang.String[]" );
        parameter.setName( "toAddresses" );

        parameter.setRequired( true );

        PluginParameterException exception =
            new PluginParameterException( mojoDescriptor, Collections.singletonList( parameter ) );

        assertEquals( "One or more required plugin parameters are invalid/missing for 'goalPrefix:goal'\n" +
        		"\n" +
        		"[0] Inside the definition for plugin 'artifactId', specify the following:\n" +
        		"\n" +
        		"<configuration>\n" +
        		"  ...\n" +
        		"  <toAddresses>\n" +
        		"    <item>VALUE</item>\n" +
        		"  </toAddresses>\n" +
        		"</configuration>.\n", exception.buildDiagnosticMessage() );
    }
