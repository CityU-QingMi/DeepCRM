    TriggerDef getTrigger(String name) {

        for (int i = triggerList.length - 1; i >= 0; i--) {
            if (triggerList[i].getName().name.equals(name)) {
                return triggerList[i];
            }
        }

        return null;
    }
