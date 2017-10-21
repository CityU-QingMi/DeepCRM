    private boolean isIgnoredLine(String line) {

        for (int i = 0; i < ignoredStarts.length; i++) {
            if (line.startsWith(ignoredStarts[i])) {
                return true;
            }
        }

        return false;
    }
