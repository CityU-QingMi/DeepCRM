    void updateAllRights(Grantee role) {

        for (int i = 0; i < map.size(); i++) {
            Grantee grantee = (Grantee) map.get(i);

            if (grantee.isRole) {
                grantee.updateNestedRoles(role);
            }
        }

        for (int i = 0; i < map.size(); i++) {
            Grantee grantee = (Grantee) map.get(i);

            if (!grantee.isRole) {
                grantee.updateAllRights();
            }
        }
    }
