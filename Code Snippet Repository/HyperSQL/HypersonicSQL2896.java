    void updateAllRights() {

        fullRightsMap.clear();

        isAdmin = isAdminDirect;

        for (int i = 0; i < roles.size(); i++) {
            Grantee currentRole = (Grantee) roles.get(i);

            addToFullRights(currentRole.fullRightsMap);

            isAdmin |= currentRole.isAdmin();
        }

        addToFullRights(directRightsMap);

        if (!isRole && !isPublic && !isSystem) {
            addToFullRights(granteeManager.publicRole.fullRightsMap);
        }
    }
