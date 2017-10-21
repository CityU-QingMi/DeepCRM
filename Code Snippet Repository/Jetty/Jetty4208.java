    private void assertRequestLog(CaptureLog captureLog)
    {
        int captureCount = captureLog.captured.size();

        if (captureCount != 1)
        {
            LOG.warn("Capture Log size is {}, expected to be 1",captureCount);
            if (captureCount > 1)
            {
                for (int i = 0; i < captureCount; i++)
                {
                    LOG.warn("[{}] {}",i,captureLog.captured.get(i));
                }
            }
            assertThat("Capture Log Entry Count",captureLog.captured.size(),is(1));
        }

        String actual = captureLog.captured.get(0);
        assertThat("Capture Log",actual,is(expectedLogEntry));
    }
