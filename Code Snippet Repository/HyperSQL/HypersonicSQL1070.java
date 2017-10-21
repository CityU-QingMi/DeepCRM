    void addTrigger(TriggerDef td, HsqlName otherName) {

        int index = triggerList.length;

        if (otherName != null) {
            int pos = getTriggerIndex(otherName.name);

            if (pos != -1) {
                index = pos + 1;
            }
        }

        triggerList = (TriggerDef[]) ArrayUtil.toAdjustedArray(triggerList,
                td, index, 1);

        TriggerDef[] list = triggerLists[td.triggerType];

        index = list.length;

        if (otherName != null) {
            for (int i = 0; i < list.length; i++) {
                TriggerDef trigger = list[i];

                if (trigger.getName().name.equals(otherName.name)) {
                    index = i + 1;

                    break;
                }
            }
        }

        list = (TriggerDef[]) ArrayUtil.toAdjustedArray(list, td, index, 1);
        triggerLists[td.triggerType] = list;
    }
