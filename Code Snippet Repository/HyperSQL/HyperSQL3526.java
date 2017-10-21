    private void createFullIndex(Session session) {

        int[] fullCols = new int[indexLimitVisible];

        ArrayUtil.fillSequence(fullCols);

        fullIndex = resultTable.createAndAddIndexStructure(session, null,
                fullCols, null, null, false, false, false);
        resultTable.fullIndex = fullIndex;
    }
