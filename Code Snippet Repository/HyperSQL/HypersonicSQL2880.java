    public boolean isAccessible(HsqlName name, int privilegeType) {

        if (isFullyAccessibleByRole(name)) {
            return true;
        }

        Right right = (Right) fullRightsMap.get(name);

        if (right == null) {
            return false;
        }

        return right.canAccess(privilegeType);
    }
