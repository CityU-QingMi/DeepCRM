    private static void addConditionsToList(HsqlArrayList list,
            Expression[] array) {

        if (array == null) {
            return;
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                if (array[i].isSingleColumnCondition
                        || array[i].isSingleColumnNull
                        || array[i].isSingleColumnNotNull) {
                    list.add(array[i]);
                }
            }
        }
    }
