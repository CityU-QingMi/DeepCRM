    private void init(final Calendar definingCalendar) {
        patterns = new ArrayList<>();

        final StrategyParser fm = new StrategyParser(definingCalendar);
        for(;;) {
            final StrategyAndWidth field = fm.getNextStrategy();
            if(field==null) {
                break;
            }
            patterns.add(field);
        }
    }
