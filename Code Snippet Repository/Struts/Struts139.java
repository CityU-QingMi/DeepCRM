    public E match(String potentialMatch) {
        E config = null;

        if (compiledPatterns.size() > 0) {
            log.debug("Attempting to match '{}' to a wildcard pattern, {} available", potentialMatch, compiledPatterns.size());

            Map<String,String> vars = new LinkedHashMap<String,String>();
            for (Mapping<E> m : compiledPatterns) {
                if (wildcard.match(vars, potentialMatch, m.getPattern())) {
                    log.debug("Value matches pattern '{}'", m.getOriginalPattern());
                    config = convert(potentialMatch, m.getTarget(), vars);
                    break;
                }
            }
        }

        return config;
    }
