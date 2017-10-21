    public void keysToArray(Object[] array) {

        Iterator it = keySet().iterator();
        int      i  = 0;

        while (it.hasNext()) {
            array[i] = it.next();

            i++;
        }
    }
