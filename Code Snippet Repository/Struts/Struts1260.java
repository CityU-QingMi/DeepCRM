    public void testNoOrdered() throws Exception {
        ParametersInterceptor pi = createParametersInterceptor();
        final Map<String, Object> actual = new LinkedHashMap<>();
        pi.setValueStackFactory(createValueStackFactory(actual));
        ValueStack stack = createStubValueStack(actual);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user.address.city", "London");
        parameters.put("user.name", "Superman");

        Action action = new SimpleAction();
        pi.setParameters(action, stack, HttpParameters.create(parameters).build());

        assertEquals("ordered should be false by default", false, pi.isOrdered());
        assertEquals(2, actual.size());
        assertEquals("London", actual.get("user.address.city"));
        assertEquals("Superman", actual.get("user.name"));

        // is not ordered
        List<Object> values = new ArrayList<Object>(actual.values());
        assertEquals("London", values.get(0));
        assertEquals("Superman", values.get(1));
    }
