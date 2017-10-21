    private boolean historize() {
        if (history == null || buffer == null) return false;
        if (history.size() > 0 &&
                history.get(history.size() - 1).equals(buffer))
            // Don't store two consecutive commands that are exactly the same.
            return false;
        history.add(buffer);
        if (history.size() <= maxHistoryLength) return true;
        history.remove(0);
        oldestHist++;
        return true;
    }
