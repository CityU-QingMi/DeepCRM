        private void closeMap() {
            for (final Iterator<Map.Entry<String, String>> it = originalValues.entrySet().iterator(); it.hasNext(); ) {
                final Map.Entry<String, String> entry = it.next();
                final String key = entry.getKey();
                final String originalValue = entry.getValue();
                if (null == originalValue) {
                    ThreadContext.remove(key);
                } else {
                    ThreadContext.put(key, originalValue);
                }
                it.remove();
            }
        }
