    private Container container() {
        final Container mock = createNiceMock(Container.class);
        ConventionsService service = createNiceMock(ConventionsService.class);

        expect(mock.getInstance(String.class, ConventionConstants.CONVENTION_CONVENTIONS_SERVICE)).andReturn("test");
        expect(mock.getInstance(ConventionsService.class, "test")).andStubReturn(service);

        ActionConfig actionConfig = null;
        expect(service.determineResultPath(actionConfig)).andReturn("");
        Map<String, ResultTypeConfig> results = new HashMap<>();
        results.put("jsp", new ResultTypeConfig.Builder("dispatcher", ServletDispatcherResult.class.getName()).build());
        expect(service.getResultTypesByExtension(packageConfiguration)).andReturn(results);

        replay(mock, service);

        return mock;
    }
