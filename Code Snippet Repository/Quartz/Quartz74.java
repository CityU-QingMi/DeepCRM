    public static List<Date> computeFireTimes(OperableTrigger trigg, org.quartz.Calendar cal,
            int numTimes) {
        LinkedList<Date> lst = new LinkedList<Date>();

        OperableTrigger t = (OperableTrigger) trigg.clone();

        if (t.getNextFireTime() == null) {
            t.computeFirstFireTime(cal);
        }

        for (int i = 0; i < numTimes; i++) {
            Date d = t.getNextFireTime();
            if (d != null) {
                lst.add(d);
                t.triggered(cal);
            } else {
                break;
            }
        }

        return java.util.Collections.unmodifiableList(lst);
    }
