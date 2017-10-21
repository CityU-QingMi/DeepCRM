    @Test
    public void testMixedMatchOrder()
    {
        PathMappings<String> p = new PathMappings<>();

        p.put(new ServletPathSpec("/"),"default");
        p.put(new ServletPathSpec("/animal/bird/*"),"birds");
        p.put(new ServletPathSpec("/animal/fish/*"),"fishes");
        p.put(new ServletPathSpec("/animal/*"),"animals");
        p.put(new RegexPathSpec("^/animal/.*/chat$"),"animalChat");
        p.put(new RegexPathSpec("^/animal/.*/cam$"),"animalCam");
        p.put(new RegexPathSpec("^/entrance/cam$"),"entranceCam");

        // dumpMappings(p);

        assertMatch(p,"/animal/bird/eagle","birds");
        assertMatch(p,"/animal/fish/bass/sea","fishes");
        assertMatch(p,"/animal/peccary/javalina/evolution","animals");
        assertMatch(p,"/","default");
        assertMatch(p,"/animal/bird/eagle/chat","animalChat");
        assertMatch(p,"/animal/bird/penguin/chat","animalChat");
        assertMatch(p,"/animal/fish/trout/cam","animalCam");
        assertMatch(p,"/entrance/cam","entranceCam");
    }
