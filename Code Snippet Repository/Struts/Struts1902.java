    public void testIsAllowed_userAllowedAndGuestDisallowed() throws Exception {
      MockHttpServletRequest request = new MockHttpServletRequest() {
        public boolean isUserInRole(String role) {
            return "user".equals(role) || "guest".equals(role);
        }
      };

      interceptor.setAllowedRoles("user"); //has to be a user
      interceptor.setDisallowedRoles("guest"); //and not a guest
      assertFalse(interceptor.isAllowed(request, null));
    }
