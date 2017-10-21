    int readRoutineParameterMode(int routineType, boolean isAggregate) {

        int parameterMode = SchemaObject.ParameterModes.PARAM_IN;

        switch (token.tokenType) {

            case Tokens.IN :
                read();
                break;

            case Tokens.OUT :
                if (routineType != SchemaObject.PROCEDURE) {
                    throw unexpectedToken();
                }

                read();

                parameterMode = SchemaObject.ParameterModes.PARAM_OUT;
                break;

            case Tokens.INOUT :
                if (routineType != SchemaObject.PROCEDURE) {
                    if (!isAggregate) {
                        throw unexpectedToken();
                    }
                }

                read();

                parameterMode = SchemaObject.ParameterModes.PARAM_INOUT;
                break;

            default :
        }

        return parameterMode;
    }
