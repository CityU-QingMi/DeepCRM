    public void grant(Session session, OrderedHashSet granteeList,
                      SchemaObject[] routines, Right right, Grantee grantor,
                      boolean withGrantOption) {

        boolean granted = false;

        for (int i = 0; i < routines.length; i++) {
            if (!grantor.isGrantable(routines[i], right)) {
                continue;
            }

            grant(session, granteeList, routines[i], right, grantor,
                  withGrantOption);

            granted = true;
        }

        if (!granted) {
            throw Error.error(ErrorCode.X_0L000,
                              grantor.getName().getNameString());
        }
    }
