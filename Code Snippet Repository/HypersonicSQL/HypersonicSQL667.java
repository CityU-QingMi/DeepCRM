    public Routine getSpecificRoutine(int paramCount) {

        for (int i = 0; i < this.routines.length; i++) {
            if (routines[i].parameterTypes.length == paramCount) {
                return routines[i];
            }
        }

        throw Error.error(ErrorCode.X_42501);
    }
