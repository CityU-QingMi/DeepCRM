    @Test
    public void testConventionUrl() throws Exception {
        // Output is filled out only for FreeMarker and Velocity templates
        // If you wanna use JSP check response.getForwardedUrl()
        String output = executeAction("/view.action");

        assertTrue(output.contains("This is the view Hello World"));

        ViewAction action = this.getAction();
        assertEquals("Hello World", action.getMessage());
    }
