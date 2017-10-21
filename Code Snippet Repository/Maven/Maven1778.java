    public void testLifecycleReader()
        throws IOException, XmlPullParserException
    {/*
        LifecycleMappingsXpp3Reader reader = new LifecycleMappingsXpp3Reader();
        LifecycleConfiguration config = reader.read( new InputStreamReader( getClass().getResourceAsStream( "/lifecycle.xml" ) ) );
        assertEquals( "check number of lifecycles", 1, config.getLifecycles().size() );
        Lifecycle l = (Lifecycle) config.getLifecycles().iterator().next();
        assertEquals( "check id", "clover", l.getId() );
        assertEquals( "check number of phases", 1, l.getPhases().size() );
        Phase p = (Phase) l.getPhases().iterator().next();
        assertEquals( "check id", "generate-sources", p.getId() );
        assertEquals( "check number of executions", 1, p.getExecutions().size() );
        Execution e = (Execution) p.getExecutions().iterator().next();
        assertEquals( "check configuration", "true", ((Xpp3Dom) e.getConfiguration()).getChild( "debug" ).getValue() );
        assertEquals( "check number of goals", 1, e.getGoals().size() );
        String g = (String) e.getGoals().iterator().next();
        assertEquals( "check goal", "clover:compiler", g );
        */
    }
