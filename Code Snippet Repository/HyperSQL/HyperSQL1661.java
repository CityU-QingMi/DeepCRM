    public void freeTableSpace(int spaceId, DoubleIntIndex spaceList,
                               long offset, long limit, boolean full) {

        totalFragmentSize += spaceList.getTotalValues()
                             * cache.getDataFileScale();

        if (full) {
            if (cache.fileFreePosition == limit) {
                cache.writeLock.lock();

                try {
                    cache.fileFreePosition = offset;
                } finally {
                    cache.writeLock.unlock();
                }
            } else {
                totalFragmentSize += limit - offset;
            }

            if (spaceList.size() != 0) {
                lookup = new DoubleIntIndex(spaceList.size(), true);

                spaceList.copyTo(lookup);
                spaceList.clear();
            }
        } else {
            spaceList.compactLookupAsIntervals();
            spaceList.setValuesSearchTarget();
            spaceList.sort();

            int extra = spaceList.size() - spaceList.capacity() / 2;

            if (extra > 0) {
                spaceList.removeRange(0, extra);

                totalFragmentSize -= spaceList.getTotalValues()
                                     * cache.getDataFileScale();
            }
        }
    }
