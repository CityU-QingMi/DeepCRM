    @Test
    public void testLevel() {
        assertNull("The level should be null (1).", this.tag.getLevel());

        this.tag.setLevel(Level.INFO);
        assertEquals("The level is not correct (1).", Level.INFO, this.tag.getLevel());

        this.tag.init();
        assertNull("The level should be null (2).", this.tag.getLevel());

        this.tag.setLevel("WARN");
        assertEquals("The level is not correct (2).", Level.WARN, this.tag.getLevel());
    }
