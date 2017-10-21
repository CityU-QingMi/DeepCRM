        public void sessionDestroyed(HttpSessionEvent se)
        {
            destroyedSessions.add(se.getSession().getId());
            if (accessAttribute)
            {
                try
                {
                   
                    se.getSession().getAttribute("anything");
                }
                catch (Exception e)
                {
                    ex = e;
                }
            }
        }
