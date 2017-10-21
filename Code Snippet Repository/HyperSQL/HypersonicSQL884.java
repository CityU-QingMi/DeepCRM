    public void addSessionVariable(ColumnSchema variable) {

        int index = sessionVariables.size();

        if (!sessionVariables.add(variable.getName().name, variable)) {
            throw Error.error(ErrorCode.X_42504);
        }

        Object[] vars = new Object[sessionVariables.size()];

        ArrayUtil.copyArray(routineVariables, vars, routineVariables.length);

        routineVariables        = vars;
        routineVariables[index] = variable.getDefaultValue(session);
    }
