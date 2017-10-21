    public void initialiseFileBlock(DoubleIntIndex spaceList,
                                    long blockFreePos, long blockLimit) {

        freshBlockFreePos = blockFreePos;
        freshBlockLimit   = blockLimit;

        if (spaceList != null) {
            spaceList.copyTo(lookup);
        }
    }
