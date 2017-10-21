    @Test
    public void testArrayPositionalParametersAreAppendedNotReplaced() {
        class ArrayPositionalParams {
            @Parameters() int[] array;
        }
        ArrayPositionalParams params = new ArrayPositionalParams();
        params.array = new int[3];
        int[] array = params.array;
        new CommandLine(params).parse("3", "2", "1");
        assertNotSame(array, params.array);
        assertArrayEquals(new int[]{0, 0, 0, 3, 2, 1}, params.array);
    }
