    private static boolean testStringRules(String source, List rules) {
        boolean matches = false;
        
        for (Object ruleObj : rules) {
            String rule;
            rule = (String) ruleObj;

            try {
                StringBuilder patternBuilder;
                patternBuilder = new StringBuilder();
                patternBuilder.append("\\b(");
                patternBuilder.append(rule);
                patternBuilder.append(")\\b");

                Pattern pattern;
                pattern = Pattern.compile(patternBuilder.toString(),
                        Pattern.CASE_INSENSITIVE);

                Matcher matcher;
                matcher = pattern.matcher(source);

                matches = matcher.find();
                if (matches) {
                    break;
                }
            }
            catch (PatternSyntaxException e) {
                matches = source.contains(rule);
                if (matches) {
                    break;
                }
            }
            finally {
                if (matches && mLogger.isDebugEnabled()) {
                    // Log the matched rule in debug mode
                    mLogger.debug("matched:" + rule + ":");
                }
            }
        }
        
        return matches;
    }   
