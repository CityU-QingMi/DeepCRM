    @Test
    public void testVelocity() {
        Velocity.init();
        final VelocityContext vContext = new VelocityContext();
        vContext.put("name", new String("Velocity"));

        final Template template = Velocity.getTemplate("target/test-classes/hello.vm");

        final StringWriter sw = new StringWriter();

        template.merge(vContext, sw);
    }
