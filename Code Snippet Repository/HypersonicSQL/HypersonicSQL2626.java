    DataFileCache getCache() {

        if (cache == null) {
            cache = new DataFileCache(database, baseFileName);

            cache.open(filesReadOnly);
        }

        return cache;
    }
