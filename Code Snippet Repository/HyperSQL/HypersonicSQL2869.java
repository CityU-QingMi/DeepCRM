    public boolean areTypesCompatible(ResultMetaData newMeta) {

        if (columnCount != newMeta.columnCount) {
            return false;
        }

        for (int i = 0; i < columnCount; i++) {
            if (!columnTypes[i].canConvertFrom(newMeta.columnTypes[i])) {
                return false;
            }
        }

        return true;
    }
