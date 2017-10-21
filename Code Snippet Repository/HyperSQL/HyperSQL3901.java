    void setResultSetProperties(Result command, Result result) {

        int required = command.rsProperties;
        int returned = result.getStatement().getResultProperties();

        if (required != returned) {
            if (ResultProperties.isUpdatable(required)) {
                if (ResultProperties.isReadOnly(returned)) {
                    session.addWarning(Error.error(ErrorCode.W_36502));
                }
            }

            if (ResultProperties.isSensitive(required)) {
                session.addWarning(Error.error(ErrorCode.W_36501));
            }

            returned = ResultProperties.addScrollable(returned,
                    ResultProperties.isScrollable(required));
            returned = ResultProperties.addHoldable(returned,
                    ResultProperties.isHoldable(required));
            result.rsProperties = returned;
        }
    }
