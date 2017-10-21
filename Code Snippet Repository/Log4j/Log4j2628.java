    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Field factoryField = null;
                int matches = 0;
                URLStreamHandlerFactory oldFactory = null;
                for (final Field field : URL.class.getDeclaredFields()) {
                    if (URLStreamHandlerFactory.class.equals(field.getType())) {
                        factoryField = field;
                        matches++;
                        factoryField.setAccessible(true);
                        oldFactory = (URLStreamHandlerFactory) factoryField.get(null);
                        break;
                    }
                }
                Assert.assertNotNull("java.net URL does not declare a java.net.URLStreamHandlerFactory field",
                        factoryField);
                Assert.assertEquals("java.net.URL declares multiple java.net.URLStreamHandlerFactory fields.", 1,
                        matches); // FIXME There is a break in the loop so always 0 or 1
                URL.setURLStreamHandlerFactory(newURLStreamHandlerFactory);
                try {
                    base.evaluate();
                } finally {
                    clearURLHandlers();
                    factoryField.set(null, null);
                    URL.setURLStreamHandlerFactory(oldFactory);
                }
            }
        };
    }
