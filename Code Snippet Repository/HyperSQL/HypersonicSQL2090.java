    public LineGroupReader(LineNumberReader reader) {

        this.sectionContinuations = defaultContinuations;
        this.sectionStarts        = ValuePool.emptyStringArray;
        this.ignoredStarts        = defaultIgnoredStarts;
        this.reader               = reader;

        try {
            getSection();
        } catch (Exception e) {}
    }
