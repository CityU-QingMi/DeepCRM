    public void checkAccess(SchemaObject object) {

        if (isFullyAccessibleByRole(object.getName())) {
            return;
        }

        HsqlName name = object.getName();

        if (object instanceof Routine) {
            name = ((Routine) object).getSpecificName();
        }

        Right right = (Right) fullRightsMap.get(name);

        if (right != null && !right.isEmpty()) {
            return;
        }

        throw Error.error(ErrorCode.X_42501, object.getName().name);
    }
