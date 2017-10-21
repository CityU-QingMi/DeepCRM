    public void testIsAllowed() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest() {
            public boolean isUserInRole(String role) {
                return "admin".equals(role);
            }
        };
        interceptor.setAllowedRoles("admin");
        assertTrue(interceptor.isAllowed(request, null));

        interceptor.setAllowedRoles("bar, admin");
        assertTrue(interceptor.isAllowed(request, null));

        interceptor.setAllowedRoles(null);
        assertTrue(interceptor.isAllowed(request, null));

        interceptor.setDisallowedRoles("bar");
        assertTrue(interceptor.isAllowed(request, null));

        interceptor.setDisallowedRoles("bar, admin");
        assertTrue(!interceptor.isAllowed(request, null));

    }
