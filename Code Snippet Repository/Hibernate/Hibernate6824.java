    @Test
    @RequiresDialect(value = {SQLServer2005Dialect.class})
    public void testManyToOneFromNonPkToNonPkSqlServer() throws Exception {
        // also tests usage of the stand-alone @JoinFormula annotation (i.e. not wrapped within @JoinColumnsOrFormulas)
        Session s = openSession();
        Transaction tx = s.beginTransaction();

        ProductSqlServer kit = new ProductSqlServer();
        kit.id = 1;
        kit.productIdnf = "KIT";
        kit.description = "Kit";
        s.persist(kit);

        ProductSqlServer kitkat = new ProductSqlServer();
        kitkat.id = 2;
        kitkat.productIdnf = "KIT_KAT";
        kitkat.description = "Chocolate";
        s.persist(kitkat);

        s.flush();
        s.clear();

        kit = (ProductSqlServer) s.get(ProductSqlServer.class, 1);
        kitkat = (ProductSqlServer) s.get(ProductSqlServer.class, 2);
        System.out.println(kitkat.description);
        assertNotNull(kitkat);
        assertEquals(kit, kitkat.getProductFamily());
        assertEquals(kit.productIdnf, kitkat.getProductFamily().productIdnf);
        assertEquals("KIT_KAT", kitkat.productIdnf.trim());
        assertEquals("Chocolate", kitkat.description.trim());

        tx.rollback();
        s.close();
    }
