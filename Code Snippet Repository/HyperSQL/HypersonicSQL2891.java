    public boolean isAccessible(HsqlName name) {

        if (isFullyAccessibleByRole(name)) {
            return true;
        }

        Right right = (Right) fullRightsMap.get(name);

        if (right != null && !right.isEmpty()) {
            return true;
        }

        if (!isPublic) {
            return granteeManager.publicRole.isAccessible(name);
        }

        return false;
    }
