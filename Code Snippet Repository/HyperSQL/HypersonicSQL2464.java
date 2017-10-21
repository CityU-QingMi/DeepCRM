    private void initialiseSpaceList() {

        int               maxId = tableIdDefault;
        OrderedIntHashSet list  = new OrderedIntHashSet();

        ba.initialise(false);

        try {
            for (;;) {
                boolean result = ba.nextBlock();

                if (!result) {
                    break;
                }

                int currentId = ba.getTableId();

                if (currentId > maxId) {
                    maxId = currentId;
                }

                if (currentId == tableIdEmpty) {
                    int freeItems    = ba.getFreeSpaceValue();
                    int freeItemsEnd = ba.getFreeBlockValue();

                    if (freeItems == 0 && freeItemsEnd == 0) {
                        emptySpaceList.addUnique(ba.currentBlockIndex);
                    } else {
                        list.add(ba.currentBlockIndex);
                    }
                }
            }
        } finally {
            ba.reset();
        }

        spaceIdSequence.set((maxId + 2) & -2);

        if (list.size() > 0) {
            setAsideBlocks(list);

            String s =
                "space manager error - recovered (freeItems in empty blocks) : ("
                + list.size() + ")";

            cache.logSevereEvent(s, null);
        }
    }
