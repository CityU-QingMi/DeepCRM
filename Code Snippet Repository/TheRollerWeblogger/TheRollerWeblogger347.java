    public boolean implies(Permission perm) {
        if (getActionsAsList().isEmpty()) {
            // new, unsaved user.
            return false;
        }
        if (perm instanceof WeblogPermission) {
            if (hasAction(ADMIN)) {
                // admin implies all other permissions
                return true;                
            } 
        } else if (perm instanceof RollerPermission) {
            RollerPermission rperm = (RollerPermission)perm;            
            if (hasAction(ADMIN)) {
                // admin implies all other permissions
                return true;
                
            } else if (hasAction(WEBLOG)) {
                // Best we've got is WEBLOG, so make sure perm doesn't specify ADMIN
                for (String action : rperm.getActionsAsList()) {
                    if (action.equals(ADMIN)) {
                        return false;
                    }
                }
                
            } else if (hasAction(LOGIN)) {
                // Best we've got is LOGIN, so make sure perm doesn't specify anything else
                for (String action : rperm.getActionsAsList()) {
                    if (action.equals(WEBLOG)) {
                        return false;
                    }
                    if (action.equals(ADMIN)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
