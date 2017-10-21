    boolean isAnyParameterSet() {

        for (int i = 0; i < parameterValues.length; i++) {
            if (parameterSet[i] != null) {
                return true;
            }
        }

        return false;
    }
