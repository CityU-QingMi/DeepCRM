    public void shutdown() {
        if (useRAMIndex) {
            scheduleIndexOperation(getSaveIndexOperation());
        } else {
            indexConsistencyMarker.delete();
        }

        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            // won't happen, since it was
        }
    }
