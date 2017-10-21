    public JobDataMap(Map<?, ?> map) {
        this();
        @SuppressWarnings("unchecked") // casting to keep API compatible and avoid compiler errors/warnings.
        Map<String, Object> mapTyped = (Map<String, Object>)map;
        putAll(mapTyped);

        // When constructing a new data map from another existing map, we should NOT mark dirty flag as true
        // Use case: loading JobDataMap from DB
        clearDirtyFlag();
    }
