    public String[] getTriggerSQL() {

        HsqlArrayList list = new HsqlArrayList();

        for (int i = 0; i < triggerList.length; i++) {
            if (!triggerList[i].isSystem()) {
                list.add(triggerList[i].getSQL());
            }
        }

        String[] array = new String[list.size()];

        list.toArray(array);

        return array;
    }
