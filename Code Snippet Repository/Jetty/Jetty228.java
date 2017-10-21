        @Override
        public void onFailure(Response response, Throwable failure)
        {
            HttpConversation conversation = ((HttpRequest)response.getRequest()).getConversation();
            // Mark the 100 Continue response as handled
            conversation.setAttribute(ATTRIBUTE, Boolean.TRUE);
            // Reset the conversation listeners to allow the conversation to be completed
            conversation.updateResponseListeners(null);

            HttpExchange exchange = conversation.getExchanges().peekLast();
            assert exchange.getResponse() == response;
            List<Response.ResponseListener> listeners = exchange.getResponseListeners();
            HttpContentResponse contentResponse = new HttpContentResponse(response, getContent(), getMediaType(), getEncoding());
            notifier.forwardFailureComplete(listeners, exchange.getRequest(), exchange.getRequestFailure(), contentResponse, failure);
        }
