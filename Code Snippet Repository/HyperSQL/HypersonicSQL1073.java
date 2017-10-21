    void removeTrigger(TriggerDef trigger) {

        TriggerDef td = null;

        for (int i = 0; i < triggerList.length; i++) {
            td = triggerList[i];

            if (td.getName().name.equals(trigger.getName().name)) {
                td.terminate();

                triggerList =
                    (TriggerDef[]) ArrayUtil.toAdjustedArray(triggerList,
                        null, i, -1);

                break;
            }
        }

        if (td == null) {
            return;
        }

        int index = td.triggerType;

        // look in each trigger in list
        for (int j = 0; j < triggerLists[index].length; j++) {
            td = triggerLists[index][j];

            if (td.getName().name.equals(trigger.getName().name)) {
                triggerLists[index] = (TriggerDef[]) ArrayUtil.toAdjustedArray(
                    triggerLists[index], null, j, -1);

                break;
            }
        }
    }
