    public Action getAction() {
        return new ActionSupport() {
            public Comparator getComparator() {
                return new Comparator() {
                    public int compare(Object o1, Object o2) {
                        Integer i1 = (Integer) o1;
                        Integer i2 = (Integer) o2;

                        return (i1.intValue() - i2.intValue());
                    }
                };
            }

            public List getSource() {
                List l = new ArrayList();
                l.add(new Integer(3));
                l.add(new Integer(1));
                l.add(new Integer(2));
                l.add(new Integer(5));
                l.add(new Integer(4));
                return l;
            }

            public Object getBadComparator() {
                return new Object();
            }

            public Object getBadSource() {
                return new Object();
            }
        };
    }
