    void checkIsSchemaObjectName() {

        if (database.sqlEnforceNames) {
            checkIsNonReservedIdentifier();
        } else {
            checkIsNonCoreReservedIdentifier();
        }

        if (database.sqlRegularNames) {
            checkIsIrregularCharInIdentifier();
        }
    }
