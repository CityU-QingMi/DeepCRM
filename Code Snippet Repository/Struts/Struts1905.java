    public void testIsAllowed_emptyAllowedAndDisallowed() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest() {
            public boolean isUserInRole(String role) {
                return "admin".equals(role);
            }
        };

        interceptor.setAllowedRoles("");//allow all
        interceptor.setDisallowedRoles("admin");
        assertFalse(interceptor.isAllowed(request, null));
    }
