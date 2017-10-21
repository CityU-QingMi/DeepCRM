    private int getKeyCount(Settings settings) {
        int count = 0;
        Iterator keyNames = settings.list();

        while (keyNames.hasNext()) {
            keyNames.next();
            count++;
        }

        return count;
    }
