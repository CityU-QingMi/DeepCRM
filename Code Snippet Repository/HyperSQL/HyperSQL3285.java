    private void cleanup() {

        try {
            if (sourceDb != null) {
                sourceDb.close();
            }

            if (targetDb != null) {
                targetDb.close();
            }
        } catch (Exception e) {}
    }
