    private void setVariables() {

        HashMappedList list = new HashMappedList();

        if (parent != null && parent.scopeVariables != null) {
            for (int i = 0; i < parent.scopeVariables.size(); i++) {
                list.add(parent.scopeVariables.getKey(i),
                         parent.scopeVariables.get(i));
            }
        }

        variablesOffset = list.size();

        for (int i = 0; i < variables.length; i++) {
            String  name  = variables[i].getName().name;
            boolean added = list.add(name, variables[i]);

            if (!added) {
                throw Error.error(ErrorCode.X_42606, name);
            }

            if (root.getParameterIndex(name) != -1) {
                throw Error.error(ErrorCode.X_42606, name);
            }
        }

        scopeVariables = list;

        RangeVariable[] parameterRangeVariables = root.getRangeVariables();
        RangeVariable range = new RangeVariable(list, null, true,
            RangeVariable.VARIALBE_RANGE);

        rangeVariables = new RangeVariable[parameterRangeVariables.length + 1];

        for (int i = 0; i < parameterRangeVariables.length; i++) {
            rangeVariables[i] = parameterRangeVariables[i];
        }

        rangeVariables[parameterRangeVariables.length] = range;
        root.variableCount                             = list.size();
    }
