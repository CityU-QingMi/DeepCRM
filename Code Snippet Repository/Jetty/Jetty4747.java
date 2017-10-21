    @Test
    public void testFoo()
    {
        JSON jsonOut = new JSON();
        JSON jsonIn = new JSON();

        jsonOut.addConvertor(Object.class, new JSONPojoConvertorFactory(jsonOut));
        jsonOut.addConvertor(Enum.class, new JSONEnumConvertor());
        jsonIn.addConvertor(Object.class, new JSONPojoConvertorFactory(jsonIn));
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

        Object obj = jsonIn.parse(new JSON.StringSource(s));

        assertTrue(obj instanceof Bar);

        Bar br = (Bar)obj;

        Baz bz = br.getBaz();

        Foo f = bz.getFoo();

        assertEquals(f, foo);
        assertTrue(br.getBazs().length==2);
        assertEquals(br.getBazs()[0].getMessage(), "baz0");
        assertEquals(br.getBazs()[1].getMessage(), "baz1");
        assertEquals(Color.Green,br.getColor());
    }
