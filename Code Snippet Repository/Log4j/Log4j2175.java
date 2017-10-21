    private boolean allLinesContain(final String text, final String containedText) {
        final String[] lines = text.split("\n");
        for (final String line : lines) {
            if (line.isEmpty()) {
                continue;
            }
            if (!line.contains(containedText)) {
                return false;
            }
        }
        return true;
    }
