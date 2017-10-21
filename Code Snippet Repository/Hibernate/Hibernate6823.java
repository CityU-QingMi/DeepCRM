	@Test
	@SkipForDialect( value = { HSQLDialect.class }, comment = "" )
	@SkipForDialect( value = { SQLServer2005Dialect.class } )
	@SkipForDialect( value = { Oracle8iDialect.class }, comment = "" )
	@SkipForDialect( value = { DB2Dialect.class }, comment = "" )
	@SkipForDialect( value = { TeradataDialect.class }, comment = "" )
	public void testManyToOneFromNonPkToNonPk() throws Exception {
		// also tests usage of the stand-alone @JoinFormula annotation (i.e. not wrapped within @JoinColumnsOrFormulas)
		Session s = openSession();
		Transaction tx = s.beginTransaction();

        Product kit = new Product();
        kit.id = 1;
        kit.productIdnf = "KIT";
        kit.description = "Kit";
        s.persist(kit);

        Product kitkat = new Product();
        kitkat.id = 2;
        kitkat.productIdnf = "KIT_KAT";
        kitkat.description = "Chocolate";
        s.persist(kitkat);

        s.flush();
        s.clear();

        kit = (Product) s.get(Product.class, 1);
        kitkat = (Product) s.get(Product.class, 2);
        System.out.println(kitkat.description);
        assertNotNull(kitkat);
        assertEquals(kit, kitkat.getProductFamily());
        assertEquals(kit.productIdnf, kitkat.getProductFamily().productIdnf);
        assertEquals("KIT_KAT", kitkat.productIdnf.trim());
        assertEquals("Chocolate", kitkat.description.trim());

        tx.rollback();
		s.close();
    }
