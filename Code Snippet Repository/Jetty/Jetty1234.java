        @Override
        public Map<Integer, Integer> onPreface(Session session)
        {
            Map<Integer, Integer> settings = new HashMap<>();
            settings.put(SettingsFrame.HEADER_TABLE_SIZE, getMaxDynamicTableSize());
            settings.put(SettingsFrame.INITIAL_WINDOW_SIZE, getInitialStreamRecvWindow());
            int maxConcurrentStreams = getMaxConcurrentStreams();
            if (maxConcurrentStreams >= 0)
                settings.put(SettingsFrame.MAX_CONCURRENT_STREAMS, maxConcurrentStreams);
            settings.put(SettingsFrame.MAX_HEADER_LIST_SIZE, getHttpConfiguration().getRequestHeaderSize());
            return settings;
        }
