    public void testIsAllowed_adminAllowedExceptManager() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest() {
            public boolean isUserInRole(String role) {
                return "admin".equals(role);
            }
        };

        interceptor.setAllowedRoles("admin");//allow all
        interceptor.setDisallowedRoles("manager");
        assertTrue(interceptor.isAllowed(request, null));
    }
