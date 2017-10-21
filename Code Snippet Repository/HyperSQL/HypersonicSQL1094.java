    public Index getFullIndex(Session session) {

        if (fullIndex == null) {
            fullIndex = getFullIndexForColumns(defaultColumnMap);

            if (fullIndex == null) {
                fullIndex = createIndexForColumns(session, defaultColumnMap);
            }
        }

        return fullIndex;
    }
