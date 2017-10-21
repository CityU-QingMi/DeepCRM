    int copy(long fileOffset, int size) throws IOException {

        // always copy the first page
        int pageCount = 0;

        if (!zeroPageSet) {
            pageCount += copy(0);

            bitMap.set(0);

            zeroPageSet = true;
        }

        if (fileOffset >= maxSize) {
            return pageCount;
        }

        long endOffset = fileOffset + size;

        if (endOffset > maxSize) {
            endOffset = maxSize;
        }

        int startPageOffset = (int) (fileOffset / pageSize);
        int endPageOffset   = (int) (endOffset / pageSize);

        if (endOffset % pageSize == 0) {
            endPageOffset--;
        }

        for (; startPageOffset <= endPageOffset; startPageOffset++) {
            pageCount += copy(startPageOffset);
        }

        return pageCount;
    }
