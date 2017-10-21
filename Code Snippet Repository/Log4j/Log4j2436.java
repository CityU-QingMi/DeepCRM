    @Test
    public void testSynopsisOrderCorrectWhenSubClassAddsParameters() {
        class BaseWithParams {
            @Parameters(index = "1") String param1;
            @Parameters(index = "0") String param0;
        }
        class SubWithParams extends BaseWithParams {
            @Parameters(index = "3") String param3;
            @Parameters(index = "2") String param2;
        }
        Help help = new Help(new SubWithParams());
        assertEquals(format("<main class> <param0> <param1> <param2> <param3>%n"), help.synopsis(0));
    }
