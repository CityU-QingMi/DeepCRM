    public void resolveTypes(Session session, Expression parent) {

        Type[] types = new Type[nodes.length];

        for (int i = 0; i < nodes.length; i++) {
            Expression e = nodes[i];

            e.resolveTypes(session, this);

            types[i] = e.dataType;
        }

        routine = routineSchema.getSpecificRoutine(types);

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].dataType == null) {
                nodes[i].dataType = routine.getParameterTypes()[i];
            }
        }

        dataType = routine.getReturnType();

        condition.resolveTypes(session, null);
    }
