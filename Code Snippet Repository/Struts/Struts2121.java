    public Action getAction() {
        return new ActionSupport() {
            public List getMyList1() {
                List l = new ArrayList();
                l.add("1");
                l.add("2");
                l.add("3");
                return l;
            }

            public List getMyList2() {
                List l = new ArrayList();
                l.add("a");
                l.add("b");
                l.add("c");
                return l;
            }

            public List getMyList3() {
                List l = new ArrayList();
                l.add("A");
                l.add("B");
                l.add("C");
                return l;
            }

            public String[] getMyArr1() {
                return new String[] { "1", "2", "3" };
            }

            public String[] getMyArr2() {
                return new String[] { "a", "b", "c" };
            }

            public String[] getMyArr3() {
                return new String[] {"A", "B", "C"};
            }
        };
    }
