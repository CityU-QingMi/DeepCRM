    @Test
    public void testExclude()
    {
        JSON json = new JSON();
        json.addConvertor(Foo.class, new JSONPojoConvertor(Foo.class,
                new String[]{"name", "long1", "int2"}));
        json.addConvertor(Bar.class, new JSONPojoConvertor(Bar.class,
                new String[]{"title", "boolean1"}));
        json.addConvertor(Baz.class, new JSONPojoConvertor(Baz.class,
                new String[]{"boolean2"}));

        Foo foo = new Foo();
        foo._name = "Foo @ " + System.currentTimeMillis();
        foo._int1 = 1;
        foo._int2 = new Integer(2);
        foo._long1 = 1000001l;
        foo._long2 = new Long(1000002l);
        foo._float1 = 10.11f;
        foo._float2 = new Float(10.22f);
        foo._double1 = 10000.11111d;
        foo._double2 = new Double(10000.22222d);
        foo._char1='a';
        foo._char2=new Character('b');

        Bar bar = new Bar("Hello", true, new Baz("World", Boolean.FALSE, foo));
        // bar.setColor(Color.Blue);

        String s = json.toJSON(bar);
        Object obj = json.parse(new JSON.StringSource(s));

        assertTrue(obj instanceof Bar);

        Bar br = (Bar)obj;
        Baz bz = br.getBaz();
        Foo f = bz.getFoo();

        assertNull(br.getTitle());
        assertFalse(bar.getTitle().equals(br.getTitle()));
        assertFalse(br.isBoolean1()==bar.isBoolean1());
        assertNull(bz.isBoolean2());
        assertFalse(bar.getBaz().isBoolean2().equals(bz.isBoolean2()));
        assertFalse(f.getLong1()==foo.getLong1());
        assertNull(f.getInt2());
        assertFalse(foo.getInt2().equals(f.getInt2()));
        assertNull(f.getName());
        assertEquals(null,br.getColor());
    }
