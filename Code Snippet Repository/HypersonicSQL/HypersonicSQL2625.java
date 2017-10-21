    boolean forceDefrag() {

        long limit = database.logger.propCacheDefragLimit
                     * cache.getFileFreePos() / 100;

        if (limit == 0) {
            return false;
        }

        long floor = database.logger.propFileSpaceValue * 1024L * 1024;

        if (floor > limit) {
            limit = floor;
        }

        long lostSize = cache.getLostBlockSize();

        return lostSize > limit;
    }
