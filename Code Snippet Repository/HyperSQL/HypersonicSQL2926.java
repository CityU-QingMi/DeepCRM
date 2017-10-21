    public void revoke(String granteeName, String roleName, Grantee grantor) {

        if (!grantor.isAdmin()) {
            throw Error.error(ErrorCode.X_42507);
        }

        Grantee grantee = get(granteeName);

        if (grantee == null) {
            throw Error.error(ErrorCode.X_28000, granteeName);
        }

        Grantee role = (Grantee) roleMap.get(roleName);

        grantee.revoke(role);
        grantee.updateAllRights();

        if (grantee.isRole) {
            updateAllRights(grantee);
        }
    }
