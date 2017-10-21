    @Test
    public void testMultipleConcurrent() throws InterruptedException {

        final int eventsCount = 10000;

        final Thread writer1 = new WriterThread(0, eventsCount / 4);
        final Thread writer2 = new WriterThread(eventsCount / 4, eventsCount / 2);
        final Thread writer3 = new WriterThread(eventsCount / 2, (3 * eventsCount) / 4);
        final Thread writer4 = new WriterThread((3 * eventsCount) / 4, eventsCount);
        writer1.start();
        writer2.start();
        writer3.start();
        writer4.start();


        final boolean[] fields = new boolean[eventsCount];
        final Thread reader1 = new ReaderThread(0, eventsCount / 4, fields);
        final Thread reader2 = new ReaderThread(eventsCount / 4, eventsCount / 2, fields);
        final Thread reader3 = new ReaderThread(eventsCount / 2, (eventsCount * 3) / 4, fields);
        final Thread reader4 = new ReaderThread((eventsCount * 3) / 4, eventsCount, fields);

        reader1.start();
        reader2.start();
        reader3.start();
        reader4.start();

        writer1.join();
        writer2.join();
        writer3.join();
        writer4.join();
        reader1.join();
        reader2.join();
        reader3.join();
        reader4.join();

        for (int i = 0; i < eventsCount; ++i) {
            Assert.assertTrue(
                "Channel contained event, but not expected message " + i,
                fields[i]);
        }
    }
