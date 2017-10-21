    public Action getAction() {
        return new ActionSupport() {
            public List getMyList() {
                List l = new ArrayList();
                l.add(new Integer(1));
                l.add(new Integer(2));
                l.add(new Integer(3));
                l.add(new Integer(4));
                l.add(new Integer(5));
                return l;
            }

            public Integer[] getMyArray() {
                Integer[] integers = new Integer[5];
                integers[0] = new Integer(1);
                integers[1] = new Integer(2);
                integers[2] = new Integer(3);
                integers[3] = new Integer(4);
                integers[4] = new Integer(5);
                return integers;
            }

            public Decider getMyDecider() {
                return new Decider() {
                    public boolean decide(Object element) throws Exception {
                        int integer = ((Integer)element).intValue();
                        return (((integer % 2) == 0)?true:false);
                    }
                };
            }
        };
    }
