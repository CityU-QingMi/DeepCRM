    private Set<Integer> assertParsesForField(String expression, int constant) {
        try {
            CronExpression cronExpression = new CronExpression(expression);
            Set<Integer> set = cronExpression.getSet(constant);
            if(set.isEmpty()) {
                fail("Empty field ["+constant+"] returned for " + expression);
            }
            return set;
        } catch(ParseException pe) {
            fail("Exception thrown during parsing: " + pe);
        }
        return null;  // not reachable
    }
