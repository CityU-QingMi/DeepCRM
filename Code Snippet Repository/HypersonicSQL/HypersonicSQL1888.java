    public boolean addAll(Object[] array) {

        boolean  result = false;
        for (int i = 0; i < array.length; i++) {
            result = true;

            add(array[i]);
        }

        return result;
    }
