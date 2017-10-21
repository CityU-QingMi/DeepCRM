    public void testOrdered() throws Exception {
        ParametersInterceptor pi = new ParametersInterceptor();
        pi.setOrdered(true);
        container.inject(pi);
        final Map<String, Object> actual = new LinkedHashMap<>();
        pi.setValueStackFactory(createValueStackFactory(actual));
        ValueStack stack = createStubValueStack(actual);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user.address.city", "London");
        parameters.put("user.address['postal']", "QJR387");
        parameters.put("user.name", "Superman");

        Action action = new SimpleAction();
        pi.setParameters(action, stack, HttpParameters.create(parameters).build());

        assertEquals(true, pi.isOrdered());
        assertEquals(3, actual.size());
        assertEquals("London", actual.get("user.address.city"));
        assertEquals("QJR387", actual.get("user.address['postal']"));
        assertEquals("Superman", actual.get("user.name"));

        // should be ordered so user.name should be first
        List<Object> values = new ArrayList<Object>(actual.values());
        assertEquals("Superman", values.get(0));
        assertEquals("London", values.get(1));
        assertEquals("QJR387", values.get(2));
    }
