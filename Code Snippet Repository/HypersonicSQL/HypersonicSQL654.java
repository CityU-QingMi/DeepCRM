    public static void createRoutines(Session session, HsqlName schema,
                                      String name) {

        Method[]  methods  = Routine.getMethods(name);
        Routine[] routines = Routine.newRoutines(session, methods);
        HsqlName routineName = session.database.nameManager.newHsqlName(schema,
            name, true, SchemaObject.FUNCTION);

        for (int i = 0; i < routines.length; i++) {
            routines[i].setName(routineName);
            session.database.schemaManager.addSchemaObject(routines[i]);
        }
    }
