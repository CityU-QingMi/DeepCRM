    public void setResultMemoryRowCount(int count) {

        if (database.logger.getTempDirectoryPath() != null) {
            if (count < 0) {
                count = 0;
            }

            resultMaxMemoryRows = count;
        }
    }
