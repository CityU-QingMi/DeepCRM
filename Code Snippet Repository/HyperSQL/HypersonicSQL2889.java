    public boolean isGrantable(SchemaObject object, Right right) {

        if (isFullyAccessibleByRole(object.getName())) {
            return true;
        }

        Right grantableRights = getAllGrantableRights(object.getName());

        return grantableRights.contains(right);
    }
