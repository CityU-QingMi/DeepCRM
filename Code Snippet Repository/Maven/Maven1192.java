    public void testReportingPluginConfig()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "reporting-plugin-config/sub" );

        assertEquals( 3, ( (List<?>) pom.getValue( "reporting/plugins[1]/configuration/stringParams" ) ).size() );
        assertEquals( "parentParam", pom.getValue( "reporting/plugins[1]/configuration/stringParams[1]/stringParam[1]" ) );
        assertEquals( "childParam", pom.getValue( "reporting/plugins[1]/configuration/stringParams[1]/stringParam[2]" ) );
        assertEquals( "  preserve space  ", pom.getValue( "reporting/plugins[1]/configuration/stringParams[1]/stringParam[3]" ) );
        assertEquals( "true", pom.getValue( "reporting/plugins[1]/configuration/booleanParam" ) );
    }
