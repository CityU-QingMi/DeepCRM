    void resetDefaultsFlag() {

        hasDefaultValues   = false;
        hasGeneratedValues = false;
        hasUpdatedValues   = false;
        hasNotNullColumns  = false;
        hasDomainColumns   = false;
        hasLobColumn       = false;

        for (int i = 0; i < columnCount; i++) {
            hasDefaultValues   |= colDefaults[i] != null;
            hasGeneratedValues |= colGenerated[i];
            hasUpdatedValues   |= colUpdated[i];
            hasNotNullColumns  |= colNotNull[i];

            if (colTypes[i].isDomainType()) {
                hasDomainColumns = true;
            }

            if (colTypes[i].isLobType()) {
                hasLobColumn = true;
            }
        }
    }
