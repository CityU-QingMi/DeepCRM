    public void testAcceptFileWithMaxSize() throws Exception {
        interceptor.setAllowedTypes("text/plain");
        interceptor.setMaximumSize(new Long(10));

        // when file is not of allowed types
        ValidationAwareSupport validation = new ValidationAwareSupport();

        URL url = ClassLoaderUtil.getResource("log4j2.xml", FileUploadInterceptorTest.class);
        File file = new File(new URI(url.toString()));
        assertTrue("log4j2.xml should be in src/test folder", file.exists());
        boolean notOk = interceptor.acceptFile(action, new StrutsUploadedFile(file), "filename", "text/html", "inputName", validation);

        assertFalse(notOk);
        assertFalse(validation.getFieldErrors().isEmpty());
        assertTrue(validation.hasErrors());
        List errors = (List) validation.getFieldErrors().get("inputName");
        assertEquals(1, errors.size());
        String msg = (String) errors.get(0);
        // the error message should contain at least this test
        assertTrue(msg.startsWith("The file is too large to be uploaded"));
        assertTrue(msg.indexOf("inputName") > 0);
        assertTrue(msg.indexOf("log4j2.xml") > 0);
    }
