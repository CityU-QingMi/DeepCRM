    public void testCauseOnly() {
        XWorkException e = new XWorkException(new IllegalArgumentException("Arg is null"));

        assertNotNull(e.getCause());
        assertNotNull(e.getLocation());
        assertEquals("com/opensymphony/xwork2/XWorkExceptionTest.java", e.getLocation().getURI());
        String s = e.getLocation().toString();
        assertTrue(s.contains("Method: testCauseOnly"));
        assertTrue(e.toString().contains("Arg is null"));
    }
