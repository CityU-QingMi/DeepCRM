    public void grant(Session session, OrderedHashSet granteeList,
                      SchemaObject dbObject, Right right, Grantee grantor,
                      boolean withGrantOption) {

        if (dbObject instanceof RoutineSchema) {
            SchemaObject[] routines =
                ((RoutineSchema) dbObject).getSpecificRoutines();

            grant(session, granteeList, routines, right, grantor,
                  withGrantOption);

            return;
        }

        HsqlName name = dbObject.getName();

        if (dbObject instanceof Routine) {
            name = ((Routine) dbObject).getSpecificName();
        }

        if (!grantor.isAccessible(dbObject)) {
            throw Error.error(ErrorCode.X_0L000,
                              grantor.getName().getNameString());
        }

        if (!grantor.isGrantable(dbObject, right)) {
            session.addWarning(Error.error(ErrorCode.W_01007,
                                           grantor.getName().getNameString()));

            return;
        }

        if (grantor.isAdmin()) {
            grantor = dbObject.getOwner();
        }

        checkGranteeList(granteeList);

        for (int i = 0; i < granteeList.size(); i++) {
            Grantee grantee = get((String) granteeList.get(i));

            grantee.grant(name, right, grantor, withGrantOption);

            if (grantee.isRole) {
                updateAllRights(grantee);
            }
        }
    }
