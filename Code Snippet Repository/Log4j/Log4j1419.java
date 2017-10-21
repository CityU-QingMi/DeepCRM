    private int finalizeConverter(final char c, final String pattern, final int start,
            final StringBuilder currentLiteral, final FormattingInfo formattingInfo,
            final Map<String, Class<PatternConverter>> rules, final List<PatternConverter> patternConverters,
            final List<FormattingInfo> formattingInfos, final boolean disableAnsi, final boolean noConsoleNoAnsi,
            final boolean convertBackslashes) {
        int i = start;
        final StringBuilder convBuf = new StringBuilder();
        i = extractConverter(c, pattern, i, convBuf, currentLiteral);

        final String converterId = convBuf.toString();

        final List<String> options = new ArrayList<>();
        i = extractOptions(pattern, i, options);

        final PatternConverter pc = createConverter(converterId, currentLiteral, rules, options, disableAnsi,
            noConsoleNoAnsi);

        if (pc == null) {
            StringBuilder msg;

            if (Strings.isEmpty(converterId)) {
                msg = new StringBuilder("Empty conversion specifier starting at position ");
            } else {
                msg = new StringBuilder("Unrecognized conversion specifier [");
                msg.append(converterId);
                msg.append("] starting at position ");
            }

            msg.append(Integer.toString(i));
            msg.append(" in conversion pattern.");

            LOGGER.error(msg.toString());

            patternConverters.add(new LiteralPatternConverter(config, currentLiteral.toString(), convertBackslashes));
            formattingInfos.add(FormattingInfo.getDefault());
        } else {
            patternConverters.add(pc);
            formattingInfos.add(formattingInfo);

            if (currentLiteral.length() > 0) {
                patternConverters
                        .add(new LiteralPatternConverter(config, currentLiteral.toString(), convertBackslashes));
                formattingInfos.add(FormattingInfo.getDefault());
            }
        }

        currentLiteral.setLength(0);

        return i;
    }
