    public static Duration parse(final CharSequence text) {
        Objects.requireNonNull(text, "text");
        final Matcher matcher = PATTERN.matcher(text);
        if (matcher.matches()) {
            // check for letter T but no time sections
            if ("T".equals(matcher.group(2)) == false) {
                final String dayMatch = matcher.group(1);
                final String hourMatch = matcher.group(3);
                final String minuteMatch = matcher.group(4);
                final String secondMatch = matcher.group(5);
                if (dayMatch != null || hourMatch != null || minuteMatch != null || secondMatch != null) {
                    final long daysAsSecs = parseNumber(text, dayMatch, SECONDS_PER_DAY, "days");
                    final long hoursAsSecs = parseNumber(text, hourMatch, SECONDS_PER_HOUR, "hours");
                    final long minsAsSecs = parseNumber(text, minuteMatch, SECONDS_PER_MINUTE, "minutes");
                    final long seconds = parseNumber(text, secondMatch, 1, "seconds");
                    try {
                        return create(daysAsSecs, hoursAsSecs, minsAsSecs, seconds);
                    } catch (final ArithmeticException ex) {
                        throw new IllegalArgumentException("Text cannot be parsed to a Duration (overflow) " + text, ex);
                    }
                }
            }
        }
        throw new IllegalArgumentException("Text cannot be parsed to a Duration: " + text);
    }
