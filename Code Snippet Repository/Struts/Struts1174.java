    public void testStringToCustomTypeUsingCustomConverterFromProperties() throws Exception {

        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader(new ClassLoader(cl) {
                @Override
                public Enumeration<URL> getResources(String name) throws IOException {
                    if ("xwork-conversion.properties".equals(name)) {
                        return new Enumeration<URL>() {
                            boolean done = false;
                            public boolean hasMoreElements() {
                                return !done;
                            }

                            public URL nextElement() {
                                if (done) {
                                    throw new RuntimeException("Conversion configuration loading " +
                                        "failed because it asked the enumeration for the next URL " +
                                        "too many times");
                                }

                                done = true;
                                return getClass().getResource("/com/opensymphony/xwork2/conversion/impl/test-xwork-conversion.properties");
                            }
                        };
                    } else {
                        return super.getResources(name);
                    }
                }
            });
            setUp();
        } finally {
            Thread.currentThread().setContextClassLoader(cl);
        }
        Bar bar = (Bar) converter.convertValue(null, null, null, null, "blah:123", Bar.class);
        assertNotNull("conversion failed", bar);
        assertEquals(123, bar.getSomethingElse());
        assertEquals("blah", bar.getTitle());
    }
