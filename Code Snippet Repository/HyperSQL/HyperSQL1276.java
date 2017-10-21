    public void valuesToArray(Object[] array) {

        Iterator it = values().iterator();
        int      i  = 0;

        while (it.hasNext()) {
            array[i] = it.next();

            i++;
        }
    }
