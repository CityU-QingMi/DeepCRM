        public void assertSearchOrder(List<String> expectedSearchOrder)
        {
            ConfigSources sources = main.getBaseHome().getConfigSources();
            List<String> actualOrder = new ArrayList<>();
            for (ConfigSource source : sources)
            {
                if (source instanceof DirConfigSource)
                {
                    actualOrder.add(source.getId());
                }
            }
            ConfigurationAssert.assertOrdered("Search Order",expectedSearchOrder,actualOrder);
        }
