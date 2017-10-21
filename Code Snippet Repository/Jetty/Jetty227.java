        @Override
        public void onSuccess(Response response)
        {
            // Handling of success must be done here and not from onComplete(),
            // since the onComplete() is not invoked because the request is not completed yet.

            Request request = response.getRequest();
            HttpConversation conversation = ((HttpRequest)request).getConversation();
            // Mark the 100 Continue response as handled
            conversation.setAttribute(ATTRIBUTE, Boolean.TRUE);

            // Reset the conversation listeners, since we are going to receive another response code
            conversation.updateResponseListeners(null);

            HttpExchange exchange = conversation.getExchanges().peekLast();
            assert exchange.getResponse() == response;
            switch (response.getStatus())
            {
                case 100:
                {
                    // All good, continue
                    exchange.resetResponse();
                    exchange.proceed(null);
                    onContinue(request);
                    break;
                }
                default:
                {
                    // Server either does not support 100 Continue,
                    // or it does and wants to refuse the request content,
                    // or we got some other HTTP status code like a redirect.
                    List<Response.ResponseListener> listeners = exchange.getResponseListeners();
                    HttpContentResponse contentResponse = new HttpContentResponse(response, getContent(), getMediaType(), getEncoding());
                    notifier.forwardSuccess(listeners, contentResponse);
                    exchange.proceed(new HttpRequestException("Expectation failed", request));
                    break;
                }
            }
        }
