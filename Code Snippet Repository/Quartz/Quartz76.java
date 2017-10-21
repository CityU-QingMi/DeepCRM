    public static List<Date> computeFireTimesBetween(OperableTrigger trigg,
            org.quartz.Calendar cal, Date from, Date to) {
        LinkedList<Date> lst = new LinkedList<Date>();

        OperableTrigger t = (OperableTrigger) trigg.clone();

        if (t.getNextFireTime() == null) {
            t.setStartTime(from);
            t.setEndTime(to);
            t.computeFirstFireTime(cal);
        }

        while (true) {
            Date d = t.getNextFireTime();
            if (d != null) {
                if (d.before(from)) {
                    t.triggered(cal);
                    continue;
                }
                if (d.after(to)) {
                    break;
                }
                lst.add(d);
                t.triggered(cal);
            } else {
                break;
            }
        }

        return java.util.Collections.unmodifiableList(lst);
    }
