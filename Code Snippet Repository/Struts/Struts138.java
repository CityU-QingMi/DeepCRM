    public void addPattern(String name, E target, boolean looseMatch) {

        Object pattern;

        if (!wildcard.isLiteral(name)) {
            if (looseMatch && (name.length() > 0) && (name.charAt(0) == '/')) {
                name = name.substring(1);
            }

            log.debug("Compiling pattern '{}'", name);

            pattern = wildcard.compilePattern(name);
            compiledPatterns.add(new Mapping<E>(name, pattern, target));

            if (looseMatch) {
                int lastStar = name.lastIndexOf('*');
                if (lastStar > 1 && lastStar == name.length() - 1) {
                    if (name.charAt(lastStar - 1) != '*') {
                        pattern = wildcard.compilePattern(name.substring(0, lastStar - 1));
                        compiledPatterns.add(new Mapping<E>(name, pattern, target));
                    }
                }
            }
        }
    }
