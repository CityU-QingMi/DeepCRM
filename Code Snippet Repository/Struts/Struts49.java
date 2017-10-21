    public void testCreate() {
        beginAt("/skill/edit.action");

        setTextField("currentSkill.name", "somename1");
        setTextField("currentSkill.description", "somedescription1");

        submit();

        beginAt("/skill/list.action");
        assertTextPresent("somename1");
        assertTextPresent("somedescription1");
    }
