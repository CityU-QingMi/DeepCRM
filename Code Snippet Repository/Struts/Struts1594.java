    public void testValidUrlCaseInsensitive() throws Exception {
        // given
        final Map<String, Object> fieldErrors = new HashMap<>();

        URLValidator validator = new URLValidator() {
            @Override
            public String getFieldName() {
                return "url";
            }

            @Override
            protected Object getFieldValue(String name, Object object) throws ValidationException {
                return object;
            }

            @Override
            protected void addFieldError(String propertyName, Object object) {
                fieldErrors.put(propertyName, object);
            }
        };

        // when
        validator.validate("http://localhost:8080/myapp");

        // then
        assertTrue(fieldErrors.isEmpty());

        // when
        validator.validate("http://LOCALHOST:8080/MYAPP");

        // then
        assertTrue(fieldErrors.isEmpty());

        // when
        validator.validate("http://www.appache.org/TEST");

        // then
        assertTrue(fieldErrors.isEmpty());
    }
