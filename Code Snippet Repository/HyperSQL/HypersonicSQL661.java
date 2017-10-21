    void resolveReferences(Session session) {

        if (statement != null) {
            statement.resolve(session);
            checkSQLData(session);
        }

        if (methodName != null && javaMethod == null) {
            boolean[] hasConnection = new boolean[1];

            javaMethod = getMethod(methodName, this, hasConnection,
                                   returnsTable);

            if (javaMethod == null) {
                throw Error.error(ErrorCode.X_46103);
            }

            javaMethodWithConnection = hasConnection[0];

            String className = javaMethod.getDeclaringClass().getName();

            if (className.equals("java.lang.Math")) {
                isLibraryRoutine = true;
            }
        }

        setReferences();
    }
