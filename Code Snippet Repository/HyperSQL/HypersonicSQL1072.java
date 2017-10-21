    public int getTriggerIndex(String name) {

        for (int i = 0; i < triggerList.length; i++) {
            if (triggerList[i].getName().name.equals(name)) {
                return i;
            }
        }

        return -1;
    }
