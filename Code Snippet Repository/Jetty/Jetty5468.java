    @Test
    public void testToArray_LazyListInput_Primitives() {
        Object input = LazyList.add(null, 22);
        input = LazyList.add(input, 333);
        input = LazyList.add(input, 4444);
        input = LazyList.add(input, 55555);

        Object arr = LazyList.toArray(input,int.class);
        assertNotNull(arr);
        assertTrue(arr.getClass().isArray());
        assertTrue(arr instanceof int[]);

        int nums[] = (int[])arr;
        assertEquals(4, nums.length);
        assertEquals(22, nums[0]);
        assertEquals(333, nums[1]);
        assertEquals(4444, nums[2]);
        assertEquals(55555, nums[3]);
    }
