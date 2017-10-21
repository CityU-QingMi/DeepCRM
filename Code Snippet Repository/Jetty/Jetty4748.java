    @Test
    public void testFoo2Map()
    {
        JSON jsonOut = new JSON();
        JSON jsonIn = new JSON();

        jsonOut.addConvertor(Object.class, new JSONPojoConvertorFactory(jsonOut,false));
        jsonOut.addConvertor(Enum.class, new JSONEnumConvertor());
        jsonIn.addConvertor(Object.class, new JSONPojoConvertorFactory(jsonIn,false));
        jsonIn.addConvertor(Enum.class, new JSONEnumConvertor());

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

        Bar bar = new Bar("Hello", true, new Baz("World", Boolean.FALSE, foo), new Baz[]{
            new Baz("baz0", Boolean.TRUE, null), new Baz("baz1", Boolean.FALSE, null)
        });
        bar.setColor(Color.Green);

        String s = jsonOut.toJSON(bar);

        assertTrue(s.indexOf("class")<0);

        Object obj = jsonIn.parse(new JSON.StringSource(s));

        assertTrue(obj instanceof Map);

        Map<String,Object> br = (Map<String,Object>)obj;

        Map<String,Object> bz = (Map<String,Object>)br.get("baz");

        Map<String,Object> f = (Map<String,Object>)bz.get("foo");
        assertTrue(f != null);
        Object[] bazs = (Object[])br.get("bazs");
        assertTrue(bazs.length==2);
        assertEquals(((Map)bazs[0]).get("message"), "baz0");
        assertEquals(((Map)bazs[1]).get("message"), "baz1");
        assertEquals("Green",br.get("color"));
    }
