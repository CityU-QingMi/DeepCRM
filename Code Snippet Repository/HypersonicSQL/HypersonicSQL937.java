    private Result executeIf(Session session) {

        Result  result  = Result.updateZeroResult;
        boolean execute = false;

        for (int i = 0; i < statements.length; i++) {
            if (statements[i].getType() == StatementTypes.CONDITION) {
                if (execute) {
                    break;
                }

                result = executeProtected(session, statements[i]);

                if (result.isError()) {
                    break;
                }

                Object value = result.getValueObject();

                execute = Boolean.TRUE.equals(value);

                i++;
            }

            result = Result.updateZeroResult;

            if (!execute) {
                continue;
            }

            result = executeProtected(session, statements[i]);
            result = handleCondition(session, result);

            if (result.isError()) {
                break;
            }

            if (result.getType() == ResultConstants.VALUE) {
                break;
            }
        }

        return result;
    }
