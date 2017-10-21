    private void setReferences() {

        OrderedHashSet set = new OrderedHashSet();

        for (int i = 0; i < parameterTypes.length; i++) {
            ColumnSchema   param = (ColumnSchema) parameterList.get(i);
            OrderedHashSet refs  = param.getReferences();

            if (refs != null) {
                set.addAll(refs);
            }
        }

        if (statement != null) {
            set.addAll(statement.getReferences());
        }

        isRecursive = false;

        if (set.contains(getSpecificName())) {
            set.remove(getSpecificName());

            isRecursive = true;
        }

        references = set;
    }
