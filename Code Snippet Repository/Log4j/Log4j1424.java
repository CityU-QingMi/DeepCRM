    @Override
    public void format(final LogEvent event, final StringBuilder toAppendTo) {
        int start = 0;
        int end = 0;
        if (!noAnsi) { // use ANSI: set prefix
            start = toAppendTo.length();
            toAppendTo.append(style);
            end = toAppendTo.length();
        }

        //noinspection ForLoopReplaceableByForEach
        for (int i = 0, size = patternFormatters.size(); i <  size; i++) {
            patternFormatters.get(i).format(event, toAppendTo);
        }

        // if we use ANSI we need to add the postfix or erase the unnecessary prefix
        if (!noAnsi) {
            if (toAppendTo.length() == end) {
                toAppendTo.setLength(start); // erase prefix
            } else {
                toAppendTo.append(defaultStyle); // add postfix
            }
        }
    }
