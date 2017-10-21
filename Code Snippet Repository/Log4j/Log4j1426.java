    protected ThrowablePatternConverter(final String name, final String style, final String[] options, final Configuration config) {
        super(name, style);
        this.options = ThrowableFormatOptions.newInstance(options);
        if (options != null && options.length > 0) {
            rawOption = options[0];
        }
        if (this.options.getSuffix() != null) {
            final PatternParser parser = PatternLayout.createPatternParser(config);
            final List<PatternFormatter> parsedFormatters = parser.parse(this.options.getSuffix());
            // filter out nested formatters that will handle throwable
            boolean hasThrowableFormatter = false;
            for (final PatternFormatter formatter : parsedFormatters) {
                if (formatter.handlesThrowable()) {
                    hasThrowableFormatter = true;
                }
            }
            if (!hasThrowableFormatter) {
                this.formatters = parsedFormatters;
            } else {
                final List<PatternFormatter> formatters = new ArrayList<>();
                for (final PatternFormatter formatter : parsedFormatters) {
                    if (!formatter.handlesThrowable()) {
                        formatters.add(formatter);
                    }
                }
                this.formatters = formatters;
            }
        } else {
            this.formatters = Collections.emptyList();
        }

    }
