    public static StandardLevel getStandardLevel(final int intLevel) {
        StandardLevel level = StandardLevel.OFF;
        for (final StandardLevel lvl : LEVELSET) {
            if (lvl.intLevel() > intLevel) {
                break;
            }
            level = lvl;
        }
        return level;
    }
