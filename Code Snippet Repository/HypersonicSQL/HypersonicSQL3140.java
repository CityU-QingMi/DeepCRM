    final int getFirstEmptyDatabaseIndex() {

        for (int i = 0; i < dbAlias.length; i++) {
            if (dbAlias[i] == null) {
                return i;
            }
        }

        return -1;
    }
