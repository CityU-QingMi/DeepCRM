        public void sessionDidActivate(HttpSessionEvent se)
        {
            RateTracker tracker = (RateTracker)se.getSession().getAttribute(__TRACKER);
            ServletContext context = se.getSession().getServletContext();
            tracker.setContext(context);
            DoSFilter filter = (DoSFilter)context.getAttribute(_filterName);
            if (filter == null)
            {
                LOG.info("No filter {} for rate tracker {}", _filterName, tracker);
                return;
            }
            addToRateTrackers(filter, tracker);
        }
