    private void dumpHeaders(String prefix, HttpTester.Message message)
    {
        LOG.debug("dumpHeaders: {}",prefix);
        Enumeration<String> names = message.getFieldNames();
        while (names.hasMoreElements())
        {
            String name = names.nextElement();
            String value = message.get(name);
            LOG.debug("dumpHeaders:   {} = {}",name,value);
        }
    }
