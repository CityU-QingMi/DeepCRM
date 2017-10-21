    public void resolve(Session session) {

        boolean resolved = false;

        switch (type) {

            case StatementTypes.ITERATE : {
                StatementCompound statement = parent;

                while (statement != null) {
                    if (statement.isLoop) {
                        if (label == null) {
                            resolved = true;

                            break;
                        }

                        if (statement.label != null
                                && label.name.equals(statement.label.name)) {
                            resolved = true;

                            break;
                        }
                    }

                    statement = statement.parent;
                }

                break;
            }
            case StatementTypes.LEAVE :
                resolved = true;
                break;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "StatementSimple");
        }

        if (!resolved) {
            throw Error.error(ErrorCode.X_42602);
        }
    }
