    public void moveDataToSpace(DataFileCache targetCache,
                                DoubleIntIndex pointerLookup) {

        int spaceId = table.getSpaceID();
        TableSpaceManager targetSpace =
            targetCache.spaceManager.getTableSpace(spaceId);

        pointerLookup.setKeysSearchTarget();

        RowIterator it = indexList[0].firstRow(this);

        while (it.next()) {
            CachedObject row = it.getCurrentRow();

            pointerLookup.addUnsorted(row.getPos(), row.getStorageSize());
        }

        pointerLookup.sort();

        for (int i = 0; i < pointerLookup.size(); i++) {
            long newPos =
                targetSpace.getFilePosition(pointerLookup.getValue(i), false);

            pointerLookup.setValue(i, (int) newPos);
        }

        it = indexList[0].firstRow(this);

        while (it.next()) {
            CachedObject row    = it.getCurrentRow();
            long         newPos = pointerLookup.lookup(row.getPos());

            // write
            targetCache.rowOut.reset();
            row.write(targetCache.rowOut, pointerLookup);
            targetCache.saveRowOutput(newPos);
        }
    }
