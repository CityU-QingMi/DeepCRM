    private Map<String, ResultTypeConfig> defaultResultsByExtension() {
        final Iterator<String> extensions = createMock(Iterator.class);
        final Set<String> keys = createMock(Set.class);
        final Map<String, ResultTypeConfig> mock = createMock(Map.class);

        expect(extensions.hasNext()).andReturn(true).times(3).andReturn(false);
        expect(extensions.next()).andReturn("default_extension").andReturn("non_default_extension")
                .andReturn("some_other_extension");

        expect(keys.iterator()).andReturn(extensions);

        expect(mock.keySet()).andReturn(keys);

        replay(extensions);
        replay(keys);
        replay(mock);

        return mock;
    }
