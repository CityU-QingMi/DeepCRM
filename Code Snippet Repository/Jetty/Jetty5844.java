    @Test
    public void testCounter()
        throws Exception
    {
        CounterStatistic count = new CounterStatistic();
        
        assertThat(count.getCurrent(),equalTo(0L));
        assertThat(count.getMax(),equalTo(0L));
        assertThat(count.getTotal(),equalTo(0L));
        
        count.increment();
        count.increment();
        count.decrement();
        count.add(4);
        count.add(-2);

        assertThat(count.getCurrent(),equalTo(3L));
        assertThat(count.getMax(),equalTo(5L));
        assertThat(count.getTotal(),equalTo(6L));
        
        count.reset();
        assertThat(count.getCurrent(),equalTo(3L));
        assertThat(count.getMax(),equalTo(3L));
        assertThat(count.getTotal(),equalTo(3L));

        count.increment();
        count.decrement();
        count.add(-2);
        count.decrement();
        assertThat(count.getCurrent(),equalTo(0L));
        assertThat(count.getMax(),equalTo(4L));
        assertThat(count.getTotal(),equalTo(4L));
     
        count.decrement();
        assertThat(count.getCurrent(),equalTo(-1L));
        assertThat(count.getMax(),equalTo(4L));
        assertThat(count.getTotal(),equalTo(4L));

        count.increment();
        assertThat(count.getCurrent(),equalTo(0L));
        assertThat(count.getMax(),equalTo(4L));
        assertThat(count.getTotal(),equalTo(5L));
    }
