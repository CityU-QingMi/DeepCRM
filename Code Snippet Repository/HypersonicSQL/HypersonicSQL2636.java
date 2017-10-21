    public void setDataFileSpaces(int value) {

        if (propFileSpaceValue == value) {
            return;
        }

        if (value != 0) {
            checkPower(value, 6);
        }

        if (value > propDataFileScale / 16) {
            value = propDataFileScale / 16;
        }

        propFileSpaceValue = value;

        if (hasCache()) {
            DataFileCache dataCache = getCache();
            boolean       result    = dataCache.setDataSpaceManager();

            if (!result) {
                return;
            }

            database.persistentStoreCollection.setNewTableSpaces();
        }
    }
