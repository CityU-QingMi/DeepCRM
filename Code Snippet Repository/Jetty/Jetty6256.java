        @Override
        public void contextDestroyed(ServletContextEvent sce)
        {
            //remove any ServerContainer beans
            if (sce.getServletContext() instanceof ContextHandler.Context)
            {
                ContextHandler handler = ((ContextHandler.Context)sce.getServletContext()).getContextHandler();
                ServerContainer bean = handler.getBean(ServerContainer.class);
                if (bean != null)
                    handler.removeBean(bean);
            }
            
            //remove reference in attributes
            sce.getServletContext().removeAttribute(javax.websocket.server.ServerContainer.class.getName());
        }
