    public String[] getSQLArray() {

        HsqlArrayList list = new HsqlArrayList();

        for (int i = 0; i < routines.length; i++) {
            list.add(routines[i].getSQL());
        }

        String[] array = new String[list.size()];

        list.toArray(array);

        return array;
    }
