    public void resolve(Session session) {

        boolean resolved = false;

        switch (type) {

            case StatementTypes.SIGNAL :
            case StatementTypes.RESIGNAL :
                resolved = true;
                break;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "StatementSignal");
        }

        if (!resolved) {
            throw Error.error(ErrorCode.X_42602);
        }
    }
