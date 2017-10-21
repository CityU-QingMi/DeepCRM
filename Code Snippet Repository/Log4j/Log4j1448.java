    static String javadocDescription(final List<LevelInfo> levels) {
        if (levels.size() == 1) {
            return "the " + levels.get(0).name + " custom log level.";
        }
        final StringBuilder sb = new StringBuilder(512);
        sb.append("the ");
        String sep = "";
        for (int i = 0; i < levels.size(); i++) {
            sb.append(sep);
            sb.append(levels.get(i).name);
            sep = (i == levels.size() - 2) ? " and " : ", ";
        }
        sb.append(" custom log levels.");
        return sb.toString();
    }
