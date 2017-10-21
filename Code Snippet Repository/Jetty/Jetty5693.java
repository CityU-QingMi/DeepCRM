    @Test
    public void testFastFail_2() throws Exception
    {
        byte[] part1 = TypeUtil.fromHexString("cebae1bdb9cf83cebcceb5f4");
        byte[] part2 = TypeUtil.fromHexString("90"); // INVALID
        // Here for test search/tracking reasons, not needed to satisfy test
        // byte[] part3 = TypeUtil.fromHexString("8080656469746564");

        Utf8StringBuilder buffer = new Utf8StringBuilder();
        // Part 1 is valid
        buffer.append(part1,0,part1.length);
        try
        {
            // Part 2 is invalid
            buffer.append(part2,0,part2.length);
            Assert.fail("Should have thrown a NotUtf8Exception");
        }
        catch (Utf8Appendable.NotUtf8Exception e)
        {
            // expected path
        }
    }
