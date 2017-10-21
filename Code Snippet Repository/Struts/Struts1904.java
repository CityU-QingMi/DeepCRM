    public void testIsAllowed_sameRoleAllowedAndDisallowed() throws Exception {
      MockHttpServletRequest request = new MockHttpServletRequest() {
          public boolean isUserInRole(String role) {
              return "admin".equals(role);
          }
      };
      
      interceptor.setAllowedRoles("admin");
      interceptor.setDisallowedRoles("admin");
      assertFalse(interceptor.isAllowed(request, null));
    }
