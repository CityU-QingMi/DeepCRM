        @Override
        public void onTimeout(AsyncEvent event) throws IOException
        {
            historyAdd("onTimeout");
            String action=event.getSuppliedRequest().getParameter("timeout");
            if (action!=null)
            {
                historyAdd(action);

                switch(action)
                {
                    case "dispatch":
                        event.getAsyncContext().dispatch();
                        break;

                    case "complete":
                        event.getSuppliedResponse().getOutputStream().println("COMPLETED\n");
                        event.getAsyncContext().complete();
                        break;

                    case "error":
                        throw new RuntimeException("error in onTimeout");
                }
            }
        }
