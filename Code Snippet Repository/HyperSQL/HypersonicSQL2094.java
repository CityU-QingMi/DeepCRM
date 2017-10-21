    private boolean isNewSectionLine(String line) {

        if (sectionStarts.length == 0) {
            for (int i = 0; i < sectionContinuations.length; i++) {
                if (line.startsWith(sectionContinuations[i])) {
                    return false;
                }
            }

            return true;
        } else {
            for (int i = 0; i < sectionStarts.length; i++) {
                if (line.startsWith(sectionStarts[i])) {
                    return true;
                }
            }

            return false;
        }
    }
