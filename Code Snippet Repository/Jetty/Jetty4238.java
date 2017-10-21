        @Override
        public void run()
        {
            if (_context == null)
            {
                LOG.warn("Unknkown context for rate tracker {}", this);
                return;
            }

            int latestIndex = _next == 0 ? (_timestamps.length - 1) : (_next - 1);
            long last = _timestamps[latestIndex];
            boolean hasRecentRequest = last != 0 && (System.currentTimeMillis() - last) < 1000L;

            DoSFilter filter = (DoSFilter)_context.getAttribute(_filterName);

            if (hasRecentRequest)
            {
                if (filter != null)
                    filter.schedule(this);
                else
                    LOG.warn("No filter {}", _filterName);
            }
            else
                removeFromRateTrackers(filter, _id);
        }
