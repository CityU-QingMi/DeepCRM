    void generateAndCheckData(Session session, Object[] data) {

        if (hasGeneratedValues) {
            setGeneratedColumns(session, data);
        }

        enforceTypeLimits(session, data);

        if (hasDomainColumns || hasNotNullColumns) {
            enforceRowConstraints(session, data);
        }
    }
