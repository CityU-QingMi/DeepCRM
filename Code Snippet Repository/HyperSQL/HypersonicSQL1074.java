    void releaseTriggers() {

        // look in each trigger list of each type of trigger
        for (int i = 0; i < TriggerDef.NUM_TRIGS; i++) {
            for (int j = 0; j < triggerLists[i].length; j++) {
                triggerLists[i][j].terminate();
            }

            triggerLists[i] = TriggerDef.emptyArray;
        }

        triggerList = TriggerDef.emptyArray;
    }
