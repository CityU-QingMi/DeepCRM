    protected boolean isAllowed(HttpServletRequest request, Object action) {
        for (String role : disallowedRoles) {
            if (request.isUserInRole(role)) {
                LOG.debug("User role '{}' is in the disallowedRoles list.", role);
                return false;
            }
        }
  
        if (allowedRoles.isEmpty()){
            LOG.debug("The allowedRoles list is empty.");
            return true;
        }
        
        for (String role : allowedRoles) {
            if (request.isUserInRole(role)) {
                LOG.debug("User role '{}' is in the allowedRoles list.", role);
                return true;
            }
        }
        
        return false;
    }
