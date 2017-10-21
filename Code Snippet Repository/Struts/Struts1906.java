    public void testAreRolesValid() throws Exception {
        RolesInterceptor roleCheckInterceptor = new RolesInterceptor(){
            List<String> validRoles = Arrays.asList(new String[]{"admin","user"});
            @Override
            public boolean areRolesValid(List<String> roles){
                return validRoles.containsAll(roles);
            }
        };
        try {
            roleCheckInterceptor.setAllowedRoles("admin, user");
            roleCheckInterceptor.setDisallowedRoles("admin, user");
        } catch (Exception e){
            fail("Valid roles should not throw an exception");
        }
        try {
            roleCheckInterceptor.setAllowedRoles("hacker, abuser");
            fail("Invalid roles should throw an exception");
        } catch (Exception e){ 
            //expected  
        }
        try {
            roleCheckInterceptor.setAllowedRoles("nonadmin, nonuser");
            fail("Invalid roles should throw an exception");
        } catch (Exception e){ 
            //expected  
        }
        try {
            roleCheckInterceptor.intercept(null);
            fail("A misconfigured should always throw an exception");
        } catch (Exception e){
            //expected;
        }
    }
