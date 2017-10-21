    long[] writeTableToDataFile(Table table) {

        RowStoreAVLDisk store =
            (RowStoreAVLDisk) table.database.persistentStoreCollection
                .getStore(table);
        long[] rootsArray = table.getIndexRootsArray();

        pointerLookup.clear();
        database.logger.logDetailEvent("lookup begins " + table.getName().name
                                       + " " + stopw.elapsedTime());
        store.moveDataToSpace(dataFileOut, pointerLookup);

        for (int i = 0; i < table.getIndexCount(); i++) {
            if (rootsArray[i] == -1) {
                continue;
            }

            long pos = pointerLookup.lookup(rootsArray[i], -1);

            if (pos == -1) {
                throw Error.error(ErrorCode.DATA_FILE_ERROR);
            }

            rootsArray[i] = pos;
        }

        // log any discrepency in row count
        long count = store.elementCount();

        if (count != pointerLookup.size()) {
            database.logger.logSevereEvent("discrepency in row count "
                                           + table.getName().name + " "
                                           + count + " "
                                           + pointerLookup.size(), null);
        }

        database.logger.logDetailEvent("table written "
                                       + table.getName().name);

        return rootsArray;
    }
