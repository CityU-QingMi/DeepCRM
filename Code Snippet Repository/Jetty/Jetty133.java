    @Test
    public void testDenyAllOnClass() throws Exception
    {

        WebAppContext wac = makeWebAppContext(DenyServlet.class.getCanonicalName(), "denyServlet", new String[]{"/foo/*", "*.foo"});

        //Assume we found 1 servlet with a @HttpConstraint with value=EmptyRoleSemantic.DENY security annotation
        ServletSecurityAnnotationHandler annotationHandler = new ServletSecurityAnnotationHandler(wac);
        AnnotationIntrospector introspector = new AnnotationIntrospector();
        introspector.registerHandler(annotationHandler);

        //set up the expected outcomes:
        //1 ConstraintMapping per ServletMapping pathSpec
        Constraint expectedConstraint = new Constraint();
        expectedConstraint.setAuthenticate(true);
        expectedConstraint.setDataConstraint(Constraint.DC_NONE);

        ConstraintMapping[] expectedMappings = new ConstraintMapping[2];

        expectedMappings[0] = new ConstraintMapping();
        expectedMappings[0].setConstraint(expectedConstraint);
        expectedMappings[0].setPathSpec("/foo/*");

        expectedMappings[1] = new ConstraintMapping();
        expectedMappings[1].setConstraint(expectedConstraint);
        expectedMappings[1].setPathSpec("*.foo");

        introspector.introspect(DenyServlet.class);

        compareResults(expectedMappings, ((ConstraintAware)wac.getSecurityHandler()).getConstraintMappings());
    }
