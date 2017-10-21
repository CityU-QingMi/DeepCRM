    public void checkObjectIsReferenced(HsqlName name) {

        OrderedHashSet set     = getReferencesTo(name);
        HsqlName       refName = null;

        for (int i = 0; i < set.size(); i++) {
            refName = (HsqlName) set.get(i);

            // except columns of same table
            if (refName.parent != name) {
                break;
            }

            refName = null;
        }

        if (refName == null) {
            return;
        }

        if (name.type == SchemaObject.CONSTRAINT) {
            return;
        }

        int errorCode = ErrorCode.X_42502;

        if (refName.type == SchemaObject.ConstraintTypes.FOREIGN_KEY) {
            errorCode = ErrorCode.X_42533;
        }

        throw Error.error(errorCode,
                          refName.getSchemaQualifiedStatementName());
    }
