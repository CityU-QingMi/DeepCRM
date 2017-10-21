    @Override
    public String getCurrentFileName(final RollingFileManager manager) {
        if (currentFileName == null) {
            final SortedMap<Integer, Path> eligibleFiles = getEligibleFiles(manager);
            final int fileIndex = eligibleFiles.size() > 0 ? (nextIndex > 0 ? nextIndex : eligibleFiles.size()) : 1;
            final StringBuilder buf = new StringBuilder(255);
            manager.getPatternProcessor().formatFileName(strSubstitutor, buf, true, fileIndex);
            final int suffixLength = suffixLength(buf.toString());
            final String name = suffixLength > 0 ? buf.substring(0, buf.length() - suffixLength) : buf.toString();
            currentFileName = name;
        }
        return currentFileName;
    }
