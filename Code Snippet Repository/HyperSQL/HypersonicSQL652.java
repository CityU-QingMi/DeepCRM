    public static Routine[] newRoutines(Session session, Method[] methods) {

        Routine[] routines = new Routine[methods.length];

        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];

            routines[i] = newRoutine(session, method);
        }

        return routines;
    }
