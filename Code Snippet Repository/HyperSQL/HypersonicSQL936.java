    private Result executeBlock(Session session) {

        Result  result = Result.updateZeroResult;
        boolean push   = !root.isTrigger();

        if (push) {
            session.sessionContext.push();

            if (hasUndoHandler) {
                String name = HsqlNameManager.getAutoSavepointNameString(
                    session.actionTimestamp, session.sessionContext.depth);

                session.savepoint(name);
            }
        }

        for (int i = 0; i < statements.length; i++) {
            result = executeProtected(session, statements[i]);
            result = handleCondition(session, result);

            if (result.isError()) {
                break;
            }

            if (result.getType() == ResultConstants.VALUE) {
                break;
            }

            if (result.getType() == ResultConstants.DATA) {
                break;
            }
        }

        if (result.getType() == ResultConstants.VALUE) {
            if (result.getErrorCode() == StatementTypes.LEAVE) {
                if (result.getMainString() == null) {
                    result = Result.updateZeroResult;
                } else if (label != null
                           && label.name.equals(result.getMainString())) {
                    result = Result.updateZeroResult;
                }
            }
        }

        if (push) {
            session.sessionContext.pop();
        }

        return result;
    }
