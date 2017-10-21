        @Override
        public void onError(AsyncEvent event) throws IOException
        {
            historyAdd("onError");
            String action=event.getSuppliedRequest().getParameter("error");
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
                }
            }
        }
