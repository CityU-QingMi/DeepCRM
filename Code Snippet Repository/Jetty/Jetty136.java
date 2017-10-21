    @Test
    public void testMethodAnnotation() throws Exception
    {
        //ServletSecurity annotation with HttpConstraint of TransportGuarantee.CONFIDENTIAL, and a list of rolesAllowed, and
        //a HttpMethodConstraint for GET method that permits all and has TransportGuarantee.NONE (ie is default)

        WebAppContext wac = makeWebAppContext(Method1Servlet.class.getCanonicalName(), "method1Servlet",  new String[]{"/foo/*", "*.foo"});

        //set up the expected outcomes: - a Constraint for the RolesAllowed on the class
        //with userdata constraint of DC_CONFIDENTIAL
        //and mappings for each of the pathSpecs
        Constraint expectedConstraint1 = new Constraint();
        expectedConstraint1.setAuthenticate(true);
        expectedConstraint1.setRoles(new String[]{"tom", "dick", "harry"});
        expectedConstraint1.setDataConstraint(Constraint.DC_CONFIDENTIAL);

        //a Constraint for the PermitAll on the doGet method with a userdata
        //constraint of DC_CONFIDENTIAL inherited from the class
        Constraint expectedConstraint2 = new Constraint();
        expectedConstraint2.setDataConstraint(Constraint.DC_NONE);

        ConstraintMapping[] expectedMappings = new ConstraintMapping[4];
        expectedMappings[0] = new ConstraintMapping();
        expectedMappings[0].setConstraint(expectedConstraint1);
        expectedMappings[0].setPathSpec("/foo/*");
        expectedMappings[0].setMethodOmissions(new String[]{"GET"});
        expectedMappings[1] = new ConstraintMapping();
        expectedMappings[1].setConstraint(expectedConstraint1);
        expectedMappings[1].setPathSpec("*.foo");
        expectedMappings[1].setMethodOmissions(new String[]{"GET"});

        expectedMappings[2] = new ConstraintMapping();
        expectedMappings[2].setConstraint(expectedConstraint2);
        expectedMappings[2].setPathSpec("/foo/*");
        expectedMappings[2].setMethod("GET");
        expectedMappings[3] = new ConstraintMapping();
        expectedMappings[3].setConstraint(expectedConstraint2);
        expectedMappings[3].setPathSpec("*.foo");
        expectedMappings[3].setMethod("GET");

        AnnotationIntrospector introspector = new AnnotationIntrospector();
        ServletSecurityAnnotationHandler annotationHandler = new ServletSecurityAnnotationHandler(wac);
        introspector.registerHandler(annotationHandler);
        introspector.introspect(Method1Servlet.class);
        compareResults (expectedMappings, ((ConstraintAware)wac.getSecurityHandler()).getConstraintMappings());
    }
