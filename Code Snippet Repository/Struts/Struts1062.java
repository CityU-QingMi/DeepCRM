    public void testCauseOnlyNoMessage() {
        XWorkException e = new XWorkException(new IllegalArgumentException());

        assertNotNull(e.getCause());
        assertNotNull(e.getLocation());
        assertEquals("com/opensymphony/xwork2/XWorkExceptionTest.java", e.getLocation().getURI());
        String s = e.getLocation().toString();
        assertTrue(s.contains("Method: testCauseOnly"));
        assertTrue(e.toString().contains("Method: testCauseOnly"));
    }
