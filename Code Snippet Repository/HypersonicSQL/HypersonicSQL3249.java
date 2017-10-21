    public void testGrowth() {

        HsqlArrayList d = new HsqlArrayList();

        for (int i = 0; i < 12; i++) {
            d.add(new Integer(i));
        }

        for (int i = 0; i < d.size(); i++) {
            System.out.println(d.get(i));
        }

        d = new HsqlArrayList();

        for (int i = 0; i < 12; i++) {
            d.add(new Integer(i));
        }

        d.set(11, new Integer(11));

        for (int i = 0; i < d.size(); i++) {
            System.out.println(d.get(i));
        }

        Iterator it = d.iterator();

        for (int i = 0; it.hasNext(); i++) {
            Integer value = (Integer) it.next();

            System.out.println(value);
            assertEquals(i, value.intValue());
        }

        //-
        assertEquals(12, d.size());
    }
