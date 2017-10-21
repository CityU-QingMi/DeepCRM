    boolean isSimpleBound() {

        if (opType == OpTypes.IS_NULL) {
            return true;
        }

        if (nodes[RIGHT] != null) {
            if (nodes[RIGHT].opType == OpTypes.VALUE) {

                // also true for all parameters
                return true;
            }

            if (nodes[RIGHT].opType == OpTypes.SQL_FUNCTION) {
                if (((FunctionSQL) nodes[RIGHT]).isValueFunction()) {
                    return true;
                }
            }
        }

        return false;
    }
